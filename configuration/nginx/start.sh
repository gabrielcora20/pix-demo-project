#!/bin/bash

check_health() {
    service_name="$1"
    health_status=$(curl -s http://"$service_name"/actuator/health | jq -r '.status')
    if [[ "$health_status" == "UP" ]]; then
        return 0
    else
        return 1
    fi
}

wait_for_healthchecks() {
    echo "Aguardando containeres dependentes..."
    for container in "$@"; do
        echo "Verificando healthcheck para $container..."
        while ! check_health "$container"; do
            sleep 1
        done
        echo "$container está pronto."
    done
    echo "Todos os containeres estão prontos. Iniciando o Nginx..."
}

containers=("presentation-primary" "presentation-secondary" "presentation-tertiary" "presentation-quaternary")

wait_for_healthchecks "${containers[@]}"

echo "Iniciando o Nginx..."
nginx -g 'daemon off;'
