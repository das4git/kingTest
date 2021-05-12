import java.util.List;

class UnluckyVassal {

    public void printReportForKing(List<String> pollResults) throws Exception {
        if(pollResults.size()==1 & pollResults.contains("")) {
            System.out.println("pollResults cannot be empty. Please, list the inhabitants of the Kingdom");
            throw new Exception();
        }
        Person king = new Person("Король");
        for (String pollResult : pollResults) {

            String[] data = pollResult.split(":");
            //регистрируем первого человека из строки
            Person person = registerAlonePerson(king, data[0]);
            //если в строке есть вассалы после : регистриуем отдельно
            if (data.length == 2) {
                registerVassals(king, person, data[1]);
            }
        }
        king.sort();
        System.out.print(king.toString());
    }


    //Регистрируем жителя королевства
    public Person registerAlonePerson(Person king, String data) {
        String name = data.trim();
        //сначала поиск среди известных вассалов короля
        Person person = king.getVassalByName(name);
        //если не найден, регистрируем
        if (person == null) {
            person = new Person(name);
        }
        //если у жителя нет господина, ставим по умолчанию - короля
        if (person.getSenior() == null) {
            king.addVassal(person);
        }
        return person;
    }

    //регистрируем вассалов для господина
    public void registerVassals(Person king, Person senior, String hisVassals) {
        String[] vassals = hisVassals.split(",");
        for (String vassal : vassals) {
            String name = vassal.trim();
            //поиск человека среди известных вассалов короля
            Person person = king.getVassalByName(name);
            //если не найден, регистрируем
            if (person == null) {
                person = new Person(name);
            }
            //и добавляем в вассалы господина
            senior.addVassal(person);
        }
    }


}