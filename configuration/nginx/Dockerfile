FROM nginx

# Instalar jq
RUN apt-get update && apt-get install -y jq

# Copiar o script de inicialização
COPY start.sh /start.sh
RUN chmod +x /start.sh

# Executar o script de inicialização quando o contêiner iniciar
CMD ["/start.sh"]