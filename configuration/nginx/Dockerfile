FROM nginx

WORKDIR /etc/nginx/

RUN apt-get update && apt-get install -y jq

COPY . .
RUN chmod +x start.sh

CMD ["/bin/bash", "-c", "/etc/nginx/start.sh"]