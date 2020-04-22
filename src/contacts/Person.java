package contacts;

public class Person extends Contact {

    private String lastName;
    private String gender;
    private String birthDay;

    Person(String name, String numberPhone, String gender, String birthDay,
           String lastName) {
        super(name, numberPhone);
        this.gender = gender;
        this.birthDay = birthDay;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    static class PersonBuilder {
        private String name;
        private String lastName;
        private String gender;
        private String birthDay;
        private String numberPhone;

        PersonBuilder() {}

        public PersonBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public PersonBuilder setBirthDay(String birthDay) {
            this.birthDay = birthDay;
            return this;
        }

        public PersonBuilder setNumberPhone(String numberPhone) {
            this.numberPhone = numberPhone;
            return this;
        }

        Person build() {
            return new Person(name, numberPhone, gender, birthDay, lastName);
        }
    }
}
