package contacts;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, String> contactsMap = new LinkedHashMap<>();
    private static final List<Contact> contactsList = new ArrayList<>();
    private static final String rgxNumber = "(?i)(^(\\+)?([0-9a-z]|\\([a-z0-9]\\))?[-|\\s]?([0-9a-z]{2,}|\\([0-9a-z]{2,}\\))?" +
            "[-|\\s]?([0-9a-z]{2,})?[-|\\s]?([0-9a-z]{2,})?[-|\\s]?([0-9a-z]{2,})?$)|^(\\(?[0-9a-z]{2,}\\)?)[-|\\s]*" +
            "(\\(?[0-9a-z]{2,}\\)?)?[-|\\s]*([0-9]{2,})?[-|\\s]*([0-9a-z]{2,})?$";
    private static final String rgxDateBirthDay = "[0-9]{4}(-|\\s)[0-1][0-9](-|\\s)[0-3][0-9]";

    public static void main(String[] args) {
        String action;
        boolean toStart = true;
        while (toStart) {
            System.out.print("Enter action (add, remove, edit, count, info, exit): >");
            action = scanner.nextLine();
            Target target = Target.valueOf(action);
            switch (target) {
                case add:
                    toAdd();
                    System.out.println();
                    break;
                case remove:
                    toRemove();
                    System.out.println();
                    break;
                case edit:
                    toEdit();
                    System.out.println();
                    break;
                case count:
                    toCount();
                    System.out.println();
                    break;
                case info:
                    toInfo();
                    System.out.println();
                    break;
                case exit:
                    toStart = false;
                    break;
            }
        }
    }

    private static void toAdd() {
        int elemOfLeftStaple = 0;
        int elemOfRightStaple = 0;
        Person.PersonBuilder personBuilder = new Person.PersonBuilder();
        Organization.OrganizationBuilder organizationBuilder = new Organization.OrganizationBuilder();
        System.out.print("Enter the type (person, organization): >");
        String action = scanner.nextLine();
        switch (action) {
            case "person":
                System.out.print("Enter the name: >");
                String namePerson = scanner.nextLine();
                personBuilder.setName(namePerson);
                System.out.print("Enter the surname: >");
                String lastName = scanner.nextLine();
                personBuilder.setLastName(lastName);
                System.out.print("Enter the birth day: >");
                String birthday = scanner.nextLine();
                if (birthday.matches(rgxDateBirthDay)) {
                    personBuilder.setBirthDay(birthday);
                } else {
                    personBuilder.setBirthDay("[no data]");
                    System.out.println("Bad birth date!");
                }
                System.out.print("Enter the gender (M, F): >");
                String gender = scanner.nextLine();
                switch (gender) {
                    case "M":
                    case "F":
                        personBuilder.setGender(gender);
                        break;
                    default:
                        personBuilder.setGender("[no data]");
                        System.out.println("Bad gender!");
                }
                System.out.print("Enter the number: >");
                String number = scanner.nextLine();
                String checkNumber = number.replaceAll("-", " ");
                char[] array = checkNumber.toCharArray();
                for (char c : array) {
                    if (c == '(') {
                        elemOfLeftStaple++;
                    } else if (c == ')') {
                        elemOfRightStaple++;
                    }
                }
                if (elemOfLeftStaple == 1 && elemOfRightStaple == 1) {
                    Pattern pattern = Pattern.compile(rgxNumber);
                    Matcher matcher = pattern.matcher(number);
                    if (matcher.find()) {
                        personBuilder.setNumberPhone(number);
                        System.out.println("The record added.");
                        contactsMap.put(namePerson + " " + lastName, number);
                        Person person = personBuilder.build();
                        contactsList.add(person);
                    } else {
                        System.out.println("Wrong number format!");
                        personBuilder.setNumberPhone("[no number]");
                        contactsMap.put(namePerson + " " + lastName, "[no number]");

                        Person person = personBuilder.build();

                        contactsList.add(person);
                    }
                } else if (elemOfLeftStaple == 0 && elemOfRightStaple == 0) {
                    Pattern pattern = Pattern.compile(rgxNumber);
                    Matcher matcher = pattern.matcher(number);
                    if (matcher.find()) {
                        personBuilder.setNumberPhone(number);
                        System.out.println("The record added.");
                        contactsMap.put(namePerson + " " + lastName, number);
                        Person person = personBuilder.build();
                        contactsList.add(person);
                    } else {
                        System.out.println("Wrong number format!");
                        personBuilder.setNumberPhone("[no number]");
                        contactsMap.put(namePerson + " " + lastName, "[no number]");

                        Person person = personBuilder.build();

                        contactsList.add(person);
                    }
                } else {
                    System.out.println("Wrong number format!");
                    personBuilder.setNumberPhone("[no number]");
                    contactsMap.put(namePerson + " " + lastName, "[no number]");

                    Person person = personBuilder.build();

                    contactsList.add(person);
                }
                break;
            case "organization":
                System.out.print("Enter the organization name: >");
                String nameOrganization = scanner.nextLine();
                organizationBuilder.setName(nameOrganization);
                System.out.print("Enter the address: >");
                String addressOrganization = scanner.nextLine();
                organizationBuilder.setAddress(addressOrganization);
                System.out.print("Enter the number: >");
                String numberOrganization = scanner.nextLine();
                String checkNumberOrganization = numberOrganization.replaceAll("-", " ");
                char[] arrayOrganization = checkNumberOrganization.toCharArray();
                for (char c : arrayOrganization) {
                    if (c == '(') {
                        elemOfLeftStaple++;
                    } else if (c == ')') {
                        elemOfRightStaple++;
                    }
                }
                if (elemOfLeftStaple == 1 && elemOfRightStaple == 1) {
                    Pattern pattern = Pattern.compile(rgxNumber);
                    Matcher matcher = pattern.matcher(numberOrganization);
                    if (matcher.find()) {
                        organizationBuilder.setNumberPhone(numberOrganization);
                        System.out.println("The record added.");
                        contactsMap.put(nameOrganization, numberOrganization);
                        Organization organization = organizationBuilder.build();
                        contactsList.add(organization);
                    } else {
                        System.out.println("Wrong number format!");
                        organizationBuilder.setNumberPhone("[no number]");
                        contactsMap.put(nameOrganization, "[no number]");

                        Organization organization = organizationBuilder.build();

                        contactsList.add(organization);
                    }
                } else if (elemOfLeftStaple == 0 && elemOfRightStaple == 0) {
                    Pattern pattern = Pattern.compile(rgxNumber);
                    Matcher matcher = pattern.matcher(numberOrganization);
                    if (matcher.find()) {
                        organizationBuilder.setNumberPhone(numberOrganization);
                        System.out.println("The record added.");
                        contactsMap.put(nameOrganization, numberOrganization);
                        Organization organization = organizationBuilder.build();
                        contactsList.add(organization);
                    } else {
                        System.out.println("Wrong number format!");
                        organizationBuilder.setNumberPhone("[no number]");
                        contactsMap.put(nameOrganization, "[no number]");

                        Organization organization = organizationBuilder.build();

                        contactsList.add(organization);
                    }
                } else {
                    System.out.println("Wrong number format!");
                    organizationBuilder.setNumberPhone("[no number]");
                    contactsMap.put(nameOrganization, "[no number]");

                    Organization organization = organizationBuilder.build();

                    contactsList.add(organization);
                }
                break;
        }
    }

    private static void toRemove() {
        if (!contactsMap.isEmpty()) {
            ArrayList<String> keys = new ArrayList<>(contactsMap.keySet());
            for (int i = 0; i < keys.size(); i++) {
                System.out.printf("%d. %s, %s\n", i + 1, keys.get(i), contactsMap.get(keys.get(i)));
            }
            System.out.print("Select a record: ");
            int num = Integer.parseInt(scanner.nextLine()) - 1;
            contactsMap.remove(keys.get(num));
            System.out.println("The record removed!");
        } else {
            System.out.println("No records to remove!");
        }
    }

    private static void toEdit() {
        if (!contactsMap.isEmpty()) {
            ArrayList<String> keys = new ArrayList<>(contactsMap.keySet());
            for (int i = 0; i < keys.size(); i++) {
                System.out.printf("%d. %s, %s\n", i+1, keys.get(i), contactsMap.get(keys.get(i)));
            }
            System.out.println("Select to record: ");
            int num = Integer.parseInt(scanner.nextLine()) - 1;
            int elemOfLeftStaple = 0;
            int elemOfRightStaple = 0;
            if (contactsList.get(num).getClass() == Person.class) {
                contactsList.get(num).setDateEdit();
                System.out.print("Select a field (name, surname, birth, gender, number): >");
                String action = scanner.nextLine();
                switch (action) {
                    case "name":
                        System.out.print("Enter the name: >");
                        String name = scanner.nextLine();
                        contactsList.get(num).setName(name);
                        contactsMap.put(name + " " + ((Person) contactsList.get(num)).getLastName(),
                                contactsList.get(num).getNumberPhone());
                        contactsMap.remove(keys.get(num));
                        System.out.println("The record updated!");
                        break;
                    case "surname":
                        System.out.print("Enter the surname: >");
                        String lastName = scanner.nextLine();
                        ((Person) contactsList.get(num)).setLastName(lastName);
                        contactsMap.put(contactsList.get(num).getName() + " " + lastName,
                                contactsList.get(num).getNumberPhone());
                        contactsMap.remove(keys.get(num));
                        System.out.println("The record updated!");
                        break;
                    case "birth":
                        System.out.println("Enter the birth: >");
                        String birthDay = scanner.nextLine();
                        if (birthDay.matches(rgxDateBirthDay)) {
                            ((Person) contactsList.get(num)).setBirthDay(birthDay);
                            System.out.println("The record updated!");
                        }
                        break;
                    case "gender":
                        System.out.println("Enter the gender (M, F): >");
                        String gender = scanner.nextLine();
                        switch (gender) {
                            case "M":
                            case "F":
                                ((Person) contactsList.get(num)).setGender(gender);
                                System.out.println("The record updated!");
                                break;
                        }
                        break;
                    case "number":
                        System.out.print("Enter the number: ");
                        String number = scanner.nextLine();
                        String checkNumber = number.replaceAll("-", " ");
                        char[] array = checkNumber.toCharArray();
                        for (char c : array) {
                            if (c == '(') {
                                elemOfLeftStaple++;
                            } else if (c == ')') {
                                elemOfRightStaple++;
                            }
                        }
                        if (elemOfLeftStaple == 1 && elemOfRightStaple == 1) {
                            Pattern pattern = Pattern.compile(rgxNumber);
                            Matcher matcher = pattern.matcher(number);
                            if (matcher.find()) {
                                System.out.println("The record updated.");
                                contactsMap.replace(keys.get(num), number);
                                contactsList.get(num).setNumberPhone(number);
                            } else  {
                                System.out.println("Wrong number format!");
                                contactsMap.replace(keys.get(num), "[no number]");
                                contactsList.get(num).setNumberPhone("[no number]");
                            }
                            break;
                        } else if (elemOfLeftStaple == 0 && elemOfRightStaple == 0) {
                            Pattern pattern = Pattern.compile(rgxNumber);
                            Matcher matcher = pattern.matcher(number);
                            if (matcher.find()) {
                                System.out.println("The record updated.");
                                contactsMap.replace(keys.get(num), number);
                                contactsList.get(num).setNumberPhone(number);
                            } else  {
                                System.out.println("Wrong number format!");
                                contactsMap.replace(keys.get(num), "[no number]");
                                contactsList.get(num).setNumberPhone("[no number]");
                            }
                            break;
                        } else  {
                            System.out.println("Wrong number format!");
                            contactsMap.replace(keys.get(num), "[no number]");
                            contactsList.get(num).setNumberPhone("[no number]");
                        }
                        break;
                }
            } else if (contactsList.get(num).getClass() == Organization.class) {
                contactsList.get(num).setDateEdit();
                System.out.print("Enter the action (name, address, number): >");
                String action = scanner.nextLine();
                switch (action) {
                    case "name":
                        System.out.print("Enter the name: >");
                        String name = scanner.nextLine();
                        contactsList.get(num).setName(name);
                        contactsMap.put(name, contactsList.get(num).getNumberPhone());
                        contactsMap.remove(keys.get(num));
                        System.out.println("The record updated!");
                        break;
                    case "address":
                        System.out.print("Enter the address: >");
                        String address = scanner.nextLine();
                        ((Organization) contactsList.get(num)).setAddress(address);
                        System.out.println("The record updated!");
                        break;
                    case "number":
                        System.out.print("Enter the number: ");
                        String number = scanner.nextLine();
                        String checkNumber = number.replaceAll("-", " ");
                        char[] array = checkNumber.toCharArray();
                        for (char c : array) {
                            if (c == '(') {
                                elemOfLeftStaple++;
                            } else if (c == ')') {
                                elemOfRightStaple++;
                            }
                        }
                        if (elemOfLeftStaple == 1 && elemOfRightStaple == 1) {
                            Pattern pattern = Pattern.compile(rgxNumber);
                            Matcher matcher = pattern.matcher(number);
                            if (matcher.find()) {
                                System.out.println("The record updated.");
                                contactsMap.replace(keys.get(num), number);
                                contactsList.get(num).setNumberPhone(number);
                            } else  {
                                System.out.println("Wrong number format!");
                                contactsMap.replace(keys.get(num), "[no number]");
                                contactsList.get(num).setNumberPhone("[no number]");
                            }
                            break;
                        } else if (elemOfLeftStaple == 0 && elemOfRightStaple == 0) {
                            Pattern pattern = Pattern.compile(rgxNumber);
                            Matcher matcher = pattern.matcher(number);
                            if (matcher.find()) {
                                System.out.println("The record updated.");
                                contactsMap.replace(keys.get(num), number);
                                contactsList.get(num).setNumberPhone(number);
                            } else  {
                                System.out.println("Wrong number format!");
                                contactsMap.replace(keys.get(num), "[no number]");
                                contactsList.get(num).setNumberPhone("[no number]");
                            }
                            break;
                        } else  {
                            System.out.println("Wrong number format!");
                            contactsMap.replace(keys.get(num), "[no number]");
                            contactsList.get(num).setNumberPhone("[no number]");
                        }
                        break;
                }
            }
        } else {
            System.out.println("No records to edit!");
        }
    }

    private static void toCount() {
        if (!contactsMap.isEmpty()) {
            System.out.println("The Phone Book has " + contactsMap.size() + " records.");
        } else {
            System.out.println("The Phone Book has 0 records.");
        }
    }

    public static void toInfo() {
        if (!contactsMap.isEmpty()) {
            ArrayList<String> keys = new ArrayList<>(contactsMap.keySet());
            for (int i = 0; i < keys.size(); i++) {
                System.out.printf("%d. %s, %s\n", i+1, keys.get(i), contactsMap.get(keys.get(i)));
            }
            System.out.print("Select a record: >");
            int num = Integer.parseInt(scanner.nextLine()) - 1;
            if (contactsList.get(num) instanceof Person) {
                System.out.println("Name: " + contactsList.get(num).getName());
                System.out.println("Surname: " + ((Person) contactsList.get(num)).getLastName());
                System.out.println("Birth date: " + ((Person) contactsList.get(num)).getBirthDay());
                System.out.println("Gender: " + ((Person) contactsList.get(num)).getGender());
                System.out.println("Number: " + contactsList.get(num).getNumberPhone());
                System.out.println("Time created: " + contactsList.get(num).getDateCreated());
//                if (contactsList.get(num).getDateEdit() != null) {
                System.out.println("Time last edit: " + contactsList.get(num).getDateEdit());
//                }
            } else if (contactsList.get(num) instanceof Organization) {
                System.out.println("Organization name: " + contactsList.get(num).getName());
                System.out.println("Address: " + ((Organization) contactsList.get(num)).getAddress());
                System.out.println("Number: " + contactsList.get(num).getNumberPhone());
                System.out.println("Time created: " + contactsList.get(num).getDateCreated());
//                if (contactsList.get(num).getDateEdit() != null) {
                System.out.println("Time last edit: " + contactsList.get(num).getDateEdit());
//                }
            }
        } else {
            System.out.println("The Phone Book has 0 records.");
        }
    }

}
