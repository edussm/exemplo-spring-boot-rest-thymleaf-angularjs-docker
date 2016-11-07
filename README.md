# Spring Boot, Security, REST, Thymleaf, AngularJS, Bootstrap, JasperReports and JPA project

## Para gerar a imagem Docker
```mvn package docker:build```

## Para executar o container
``` docker run -p 8080:8080 -v PASTA_DO_PROJETO/jasper/:/jasper/ -it --rm exemplo-eiiv/springboot-example```

## Executar um shell no container que já está rodando
```docker exec -it ID_DO_CONTAINER /bin/ash```

## Acesso
Login: demo
Password: demo

