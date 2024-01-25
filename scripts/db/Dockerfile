FROM mysql/mysql-server:8.0.31

MAINTAINER 1

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./bothsavage.sql /docker-entrypoint-initdb.d

COPY ./bothsavage_codegen.sql /docker-entrypoint-initdb.d

COPY ./bothsavage_config.sql /docker-entrypoint-initdb.d

COPY ./bothsavage_job.sql /docker-entrypoint-initdb.d
