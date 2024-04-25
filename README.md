# Практическая работа по Docker

* Backend
localhost:8080
* Database
localhost:5432

### Get запросы
Посмотреть список всех книг
```
http://localhost:3000/Book/allBook
```
Посмотреть книгу по id
```
http://localhost:3000/Book/getBook/{id}
```

### Post запрос
Сохранить книгу
```
http://localhost:3000/Book/safeBook/@RequestBody Book
```

### Put запрос
Изменить книгу
```
http://localhost:3000/Book/editBook/{id}/@RequestBody Book
```

### Delete запрос
Удалить книгу
```
http://localhost:3000/Book/deleteBook/{id}
```
