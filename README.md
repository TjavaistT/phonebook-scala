
 Phonebook - книга контактов
-------------------------------------------


* [Описание](#Описание)
* [Развертывание приложения](#Развертывание)
* [Описание Rest Api](#Описание_Rest_Api)
* [Пример использования Rest_Api](#Пример_использования_Rest_Api)
* [Описание web интерфейса](#Описание_web_интерфейса)

### Развертывание
Приложение является компилируемым docker-образом с бд postgress и фреймворком Play на сборщике sbt. 
Запуск осуществляется только из-под Linux

В корневой папке приложения запустить команду (Linux) 
```bash
sh start.sh
```

Открыть браузер по адресу -  <http://localhost:9000/>.  
 
### Rest_Api

- получить все контакты 
    ```
    GET /phones
    ``` 
 
- поиск по телефону 
 
    ``` 
    GET /phones/searchBySubstr?phoneSubstring=<...>
    ``` 
                            
- поиск по имени    

    ``` 
    GET /phones/searchBySubstr?nameSubstring=<...>
    ``` 
- добавить контакт (name="...", phone="...")

    ```
    POST /phones/createNewPhone 
    ```
- получить один контакт 
    
    ```
    GET /phone/{id}
    ``` 
  
- удалить контакт  

    ``` 
    DELETE /phone/{id}
    ``` 
- обновить контакт

    ```
    POST /phone/{id}
    ``` 

### Пример_использования_Rest_Api

- добавить контакт
```bash
curl http://localhost:9000/phones/createNewPhone -X POST -d 'name=Зигфрид&phoneNumber=02'
```

- получить все контакты 
```
curl http://localhost:9000/phones 
```
- пример ответа 
```
[{"id":"04b3079b-d503-45b7-9190-6c88a867fc88","name":"Зигфрид","number":2},
{"id":"232944ee-7998-4529-b5b6-c80d7d38f094","name":"Брумгильда","number":3}]
```
- редактировать контакт
```
curl  http://localhost:9000/phone/04b3079b-d503-45b7-9190-6c88a867fc88 -X POST -d 'name=Рейнхарт&phoneNumber=79780502211' 
```
- поиск контактов

```
$ curl http://localhost:9000/phones/searchBySubstr?nameSubstring=фрид&phoneSubstring=2
```

- пример ответа 
```
[{"id":"04b3079b-d503-45b7-9190-6c88a867fc88","name":"Зигфрид","number":2},
{"id":"9d523862-8773-4c0d-bafc-c6d4a51a89b6","name":"Фридланд","number":7254}]
```
- удалить запись по id
```
$ curl http://localhost:9000/phone/9d523862-8773-4c0d-bafc-c6d4a51a89b6 -X DELETE
```

### Описание_web_интерфейса
Одностраничный web интерфейс с списком контактов, формами поиска, добавления, редактирования и удаления контактов
![alt-текст][phonebook-interface]

### Используемые технологии
- Docker
- Postgress
- sbt, PlayFramework, Slick, Akka

[phonebook-interface]: https://github.com/TjavaistT/phonebook-scala/blob/master/phonebook-scala%20interface.png "Интерфейс приложения"