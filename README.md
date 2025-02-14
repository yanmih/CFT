## Тестовое заданиепо JAVA
1. Версия Java: openjdk 18.0.2
   
## Параметры запуска программы
1. -o задаёт путь для результатов.(Если задали этот параметр, необходимо прописать путь куда бкдкт записываться файлы)(необязательный)
2. -p задает префикс имен выходных файлов.(необязательный)
3. -a задаём режим добавления в существующие файлы.(по умолчанию данные перезаписываются)(необязательный)
4. -s краткая статистика. Выводит количество элементов записанных в исходящие файлы. (необязательный)
5. -f полная статистика. Выводит количество элементов и дополнительно содержит минимальное и максимальное значения, сумма и среднее. Полная статистика для строк, помимо их количества, содержит также размер самой
короткой строки и самой длинной.
6. обязательно надо передать файлы *.txt 

## Пример запуска утилиты
 
#### D:\Project\CFTTest>java -jar CFTTest.jar -s -p prefix_ in1.txt in2.txt
 
Количество элементов выгруженные в файл:

Целые числа - 3

Вещественные числа - 3

Строки - 6

------------------------------------------
Выгрузка данных завершена в директорию - D:\Project\CFTTest

По умолчанию файлы с результатами располагаются в текущей папке с именами integers.txt, floats.txt, strings.txt (в данном примере путь по умолчанию D:\Project\CFTTest)
___
### Примеры некорректного запуска утилиты
#### D:\2\1\Project\CFTTest>java -jar CFTTest.jar -s -p prefix_
В параметрах не указаны входные файлы ! -  *.txt

#### D:\2\1\Project\CFTTest>java -jar CFTTest.jar
В командной строке не переданы аргументы

##### D:\2\1\Project\CFTTest>java -jar CFTTest.jar -s -p prefix_ in1.txt in3.txt
Error reading files: D:\2\1\Project\CFTTest\in3.txt (Не удается найти указанный файл)
___


