# Jan, 2021 – Feb, 2021

## Project Roles

Backend developer, frontend developer

## Project

A working sample of travel agency website as a summary task project upon completion of a Java course

## Participation

Designed project's architecture using MVC (Servlet API), DAO, Factory, Command patterns.
Implemented authentication and authorization, session handling.
Created filters.
Created DB structure.
Created frontend pages using JSP, JSTL.
Implemented localization for English and Russian languages;
Implemented server-side input validation.

## Database

MySQL

## Tools

- Eclipse IDE;
- Git;
- Apache Tomcat;
- Apache Maven;
- MySQL Workbench;

## Technologies

Java 8, Java EE, Log4j, JDBC, SQL, JSP + JSTL.

# Туристична агенція

Турагенство має каталог Туров. Для каталогу реалізувати можливість вибірки турів:
- за типом (відпочинок, екскурсія, шопінг);
- за ціною;
- за кількістю осіб;
- за типом готелю.
Користувач реєструється в системі, обирає Тур і робить Замовлення. Після замовлення тур має статус 'зареєстрований'.
Незареєстрований користувач не має можливості замовляти тур.
Користувач має особистий кабінет, в якому міститься коротка інформація про нього, а також список обраних турів і їх поточний статус (зареєстрований, сплачений, скасований).
Менеджер визначає тур як 'палаючий'. 'Палаючі' тури завжди відображаються нагорі переліку. Менеджер переводить статус туру з 'зареєстрований' у 'оплачений' або 'скасований'. На кожен замовлений тур визначається знижка з кроком, який встановлюється менеджером, але не більше відсотка, який так само визначає менеджер.
Адміністратор системи володіє такими ж правами, як і менеджер, а додатково може:
- додати/видалити тур, змінити інформацію про тур;
- заблокувати/розблокувати користувача.