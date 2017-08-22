# platform

## start registry
--server.port=8761
--eureka.instance.hostname=registry1.kimloong.me
--eureka.client.register-with-eureka=true
--eureka.client.fetch-registry=true
--eureka.client.service-url.defaultZone=http://registry2.kimloong.me:8762/eureka/