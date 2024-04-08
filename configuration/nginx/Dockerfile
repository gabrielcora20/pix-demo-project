FROM nginx

WORKDIR /etc/nginx/

RUN apt-get update && apt-get install -y jq

COPY . .
RUN chmod +x /etc/nginx/start.sh

CMD ["bash", "-c", "/etc/nginx/start.sh"]