package kpi.lab2.model.entity;

import java.util.Objects;

public class Car {
    private int id;
    private Manufacturer manufacturer;
    private String model;
    private float fuelConsumption;
    private float kilometrage;
    private int year;
    private float fuelCapacity;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getFuelConsumption() {
        return fuelConsumption;
    }


    public void setFuelConsumption(float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public float getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(float kilometrage) {
        this.kilometrage = kilometrage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(float fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }




    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(model, car.model) &&
                Objects.equals(fuelConsumption, car.fuelConsumption) &&
                Objects.equals(kilometrage, car.kilometrage) &&
                Objects.equals(manufacturer, car.manufacturer) &&
                Objects.equals(year, car.year) &&
                Objects.equals(fuelCapacity, car.fuelCapacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, fuelConsumption, kilometrage, manufacturer, year, fuelCapacity);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", fuelConsumption='" + fuelConsumption + '\'' +
                ", kilometrage='" + kilometrage + '\'' +
                ", manufacturer=" + manufacturer +
                ", year=" + year +
                ", fuelCapacity=" + fuelCapacity +
                '}';
    }

    public static class Builder{
        Car instance = new Car();

        public Builder setId(int id) {
            instance.id = id;
            return this;
        }

        public Builder setModel(String model) {
            instance.model = model;
            return this;
        }

        public Builder setFuelConsumption(float fuelConsumption) {
            instance.fuelConsumption = fuelConsumption;
            return this;
        }

        public Builder setKilometrage(float kilometrage) {
            instance.kilometrage = kilometrage;
            return this;
        }

        public Builder setManufacturer(Manufacturer manufacturer) {
            instance.manufacturer = manufacturer;
            return this;
        }

        public Builder setYear(int year) {
            instance.year = year;
            return this;
        }

        public Builder setFuelCapacity(float fuelCapacity) {
            instance.fuelCapacity = fuelCapacity;
            return this;
        }

        public Car build() {
            return instance;
        }
    }
}
