package contacts;

public class Organization extends Contact {
    private String address;

    Organization(String name, String numberPhone, String address) {
        super(name, numberPhone);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    static class OrganizationBuilder {
        private String name;
        private String address;
        private String numberPhone;

        OrganizationBuilder() {}

        public OrganizationBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public OrganizationBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public OrganizationBuilder setNumberPhone(String numberPhone) {
            this.numberPhone = numberPhone;
            return this;
        }

        Organization build() {
            return new Organization(name, numberPhone, address);
        }
    }
}
