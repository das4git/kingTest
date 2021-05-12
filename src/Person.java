import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Person {
    private String name;
    private Person Senior;
    private List<Person> vassalsList = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public Person getSenior() {
        return Senior;
    }

    public void setSenior(Person senior) {
        this.Senior = senior;
    }

    public List<Person> getVassalsList() {
        return vassalsList;
    }

    //добавляем вассала к господину
    public void addVassal(Person vassal) {
        //при надобности правим список вассалов на верхнем уровне
        if (vassal.getSenior() != null) {
            vassal.getSenior().getVassalsList().remove(vassal);
        }
        vassal.setSenior(this);
        vassalsList.add(vassal);
    }

    // ищем человека по имени среди зарегистрированных, используя рекурсию
    public Person getVassalByName(String name) {
        if (this.name.equals(name)) { 
            return this;
        }
        for (Person vassal : vassalsList) {
            Person wanted = vassal.getVassalByName(name);
            if (wanted != null) {
                return wanted;
            }
        }
        return null;
    }

    //сортируем по имени все иерархии, используя рекурсию
    public void sort() {
        vassalsList.sort(Comparator.comparing(person -> person.name));
        for (Person vassal : vassalsList) {
            vassal.sort();
        }
    }

    //рекурсивно выводим людей по всем узлам
    public String toString() {
        StringBuilder report = new StringBuilder();

        Person person = this.Senior;
        while (person != null) {
            report.append("   ");
            person = person.getSenior();
        }
        report.append(name + "\n");
        for (Person vassal : vassalsList) {
            report.append(vassal.toString());
        }
        return report.toString();
    }
}