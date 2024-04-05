package com.gabrielcora.pix.payment.infra.data.context

import com.gabrielcora.pix.payment.domain.events.Event
import com.gabrielcora.pix.payment.domain.models.DomainEntity
import com.gabrielcora.pix.payment.infra.crosscutting.bus.interfaces.EventBus
import com.gabrielcora.pix.payment.infra.data.interfaces.IMongoContext
import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component
import java.lang.System.gc

@Component
class MongoContext(
    private val eventBus: EventBus,
    override val db: MongoTemplate
) : IMongoContext {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val domainCommands = mutableListOf<DomainCommand>()

    override fun addCommand(func: suspend () -> Unit, entity: DomainEntity) {
        domainCommands.add(DomainCommand(func, entity))
    }

    override suspend fun saveChanges(): Int {
        logger.info("Executing commit method in class: ${javaClass.simpleName}")

        val result: Int = runBlocking {
            try {
                coroutineScope {
                    val tasks = domainCommands.map { domainCommand ->
                        async {
                            domainCommand.command()
                            publishDomainEvents(domainCommand.entity.occurredEvents().toList())
                        }
                    }
                    tasks.awaitAll()
                    tasks.count()
                }
            } catch (ex: Exception) {
                logger.error("Failed to save changes", ex)
                0
            }
        }
        return result
    }

    private suspend fun publishDomainEvents(domainEvents: List<Event>) {
        logger.info("Executing publishDomainEvents method in class: ${javaClass.simpleName}")

        domainEvents.forEach { domainEvent ->
            try {
                eventBus.send(domainEvent)
            } catch (ex: Exception) {
                logger.error("Failed to publish event", ex)
            }
        }
    }

    override fun close() {
        gc()
    }

    override suspend fun commit(): Boolean {
        return saveChanges() > 0;
    }
}