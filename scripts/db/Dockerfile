FROM mysql/mysql-server:8.0.31

MAINTAINER 1

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./bothSavage.sql /docker-entrypoint-initdb.d

COPY ./bothSavage_codegen.sql /docker-entrypoint-initdb.d

COPY ./bothSavage_config.sql /docker-entrypoint-initdb.d

COPY ./bothSavage_job.sql /docker-entrypoint-initdb.d
