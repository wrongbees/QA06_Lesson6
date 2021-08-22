package tests.db;

public class DBQueries {
    /*
Задание 1
Запросы для таблицы Customer:

1. Вывести всех, кточто живет в Лондоне

SELECT *
FROM Customers
WHERE City='London';

2. Выбрать имена контактов и имена заказчиков, где адрес заканчивается на 23

SELECT CustomerName, ContactName
FROM Customers
WHERE Address like '%23';
3. Выбрать уникальные города.

SELECT DISTINCT City
FROM Customers;


4. Выбрать тех пользователей, у кого код начинается с 0 (нуль)

SELECT *
FROM Customers
WHERE PostalCode like '0%';

5. Вывести клиентов не из США

SELECT *
FROM Customers
WHERE Country != 'USA';

6. Вывести всех, кто из Франции и отсортировать по убыванию по имени контакта

SELECT *
FROM Customers
WHERE Country = 'France'
ORDER BY CustomerName DESC;

7. Вывести клиентов из Германии и США, ограничить выбор 10 записями

SELECT *
FROM Customers
WHERE Country in ('USA','Germany')
LIMIT 20;

Задание 2

Запросы для таблицы Products:

1. Выбрать все продукты, начинающиеся на букву «М»

SELECT *
FROM Products
WHERE ProductName like 'M%';

2. Вывести характеристику упаковки (unit) для товара Steeleye Stout

SELECT Unit
FROM Products
WHERE ProductName = 'Steeleye Stout';

3. Вывести названия товаров, цена которых выше 22

SELECT ProductName
FROM Products
WHERE Price > 22;

4. Вывести товары, в которых вес упаковки составляет 500 g

SELECT *
FROM Products
WHERE Unit like '%500 g%';

5. Вывести товары, упаковка которых состоит из «bottles»

SELECT *
FROM Products
WHERE Unit like '%bottles';

6. Вывести товары, где SupplierID составляет 7 и отсортировать результаты по убыванию по цене

SELECT *
FROM Products
WHERE SupplierID = 7
ORDER BY Price DESC;


Задание 3

    На веб-странице существует кнопка «Быстрый поиск», которая выделяет из таблицы character
в базе данных всех персонажей выше 45 уровня (столбец level), расы dwarf (столбец race) и
выводит результат на страницу. Укажите, как будет выглядеть в данном случае SQL-запрос.

SELECT *
FROM character ch
WHERE ch.race = 'dwarf' and ch.level > 7;


Задание 4

Запросы для таблицы Employees:
1. Вывести имя, фамилию и записи о сотруднике Leverling

SELECT *
FROM Employees
WHERE LastName = 'Leverling';

2. Вывести информацию по работникам старше 1960 года

SELECT *
FROM Employees
WHERE BirthDate < '1960-01-01';

3. Вывести  дату рождения сотрудников, чьи имена начинаются на букву «А

SELECT BirthDate
FROM Employees
WHERE FirstName like 'A%';

4. Вывести имя, фамилию и дату рождения сотрудников, отсортировав по дате рождения
 по возрастанию

SELECT FirstName, LastName, BirthDate
FROM Employees
ORDER BY BirthDate ASC;



     */
}
