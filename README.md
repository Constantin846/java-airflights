# Java Airflights
### Описание
Приложение состовляет статистику о авиаперелетах.

В качестве входных данных используется JSON файл, 
в котором указывается список членов экипажа и список перелетов.
Пример входного файла представлен здесь: [load-file.json](src\test\resources\load-file.json)

В качестве выходных данных создается JSON файл,
в котором для каждого члена экипажа показано суммарное время полета
по месяцам, а также отмечено: превышает ли суммарное время полета 80 часов;
есть ли в данном месяце неделя (7 подряд идущих дней), для которой суммарное 
время полета превышает 36 часов; существует ли в месяце день с суммарным временем
полета более 8 часов.
Пример выходного файла представлен здесь: [stats-file.json](src\test\resources\stats-file.json)

Все время полета засчитывается в день взлета.

### Запуск проекта
* Склонируйте репозиторий: git clone https://github.com/Constantin846/java-airflights.git
* Перейдите в директорию проекта: cd java-se-tz-airflights
* Создайте исполняемый jar: mvn clean package
* Запустите исполняемый jar:  
  * #### Вариант 1
      + Положите входной файл в папку [src\main\resources](src\main\resources)
      + Выполните команду: java -jar target/java-se-tz-airflights-1.0-SNAPSHOT.jar
      + Программа создаст stats-file.json файл в той же папке [src\main\resources](src\main\resources)
  * #### Вариант 2
    + Укажите путь для входного файла (your-path), используя параметр запуска <span style="color:#59afe1">load-file-path</span>

      **<span style="color:red">Внимание!</span>** Путь не должен содержать пробелы!
    + Выполните команду: java -jar target/java-se-tz-airflights-1.0-SNAPSHOT.jar load-file-path your-path
    + Программа создаст stats-file.json файл в папке, где находится входной файл 
  * #### Вариант 3
    + Укажите путь для входного файла (your-path), используя параметр запуска <span style="color:#59afe1">load-file-path</span>
    + Укажите путь и название для выходного файла с разрешением .json (create-path.json) , используя параметр запуска <span style="color:#59afe1">create-file-path</span>

      **<span style="color:red">Внимание!</span>** Путь не должен содержать пробелы!
    + Выполните команду: java -jar target/java-se-tz-airflights-1.0-SNAPSHOT.jar load-file-path your-path create-file-path create-path.json
    + Программа создаст файл create-path.json

### Технологии
* Java
* JUnit
* Maven