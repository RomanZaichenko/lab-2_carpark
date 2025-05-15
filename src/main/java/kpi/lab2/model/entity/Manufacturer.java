package kpi.lab2.model.entity;

import java.util.Objects;

public class Manufacturer {
    private Integer id;
    private String name;
    private String country;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer manufacturer = (Manufacturer) o;
        return Objects.equals(id, manufacturer.id) && Objects.equals(name, manufacturer.name) &&
                Objects.equals(country, manufacturer.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public static class Builder{
        Manufacturer instance = new Manufacturer();

        public Builder setId(int id) {
            instance.id = id;
            return this;
        }

        public Builder setName(String name) {
            instance.name = name;
            return this;
        }

        public Builder setCountry(String country) {
            instance.country = country;
            return this;
        }

        public Manufacturer build() {
            return instance;
        }
    }
}
