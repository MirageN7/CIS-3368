package sample;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

public class Employee implements Worker
{
    public String firstName;
    public String lastName;
    public String email;
    public String role;
    public String hiredate;
    public UUID id;
    public boolean isActive;
    public String status;
    public String notes;

    @Override
    public String toString()
    {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public void hire()
    {
        isActive = true;
    }

    @Override
    public void fire()
    {
        isActive = false;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
