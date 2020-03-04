package com.telwood;

//import com.telwood.Department;
//import com.telwood.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<String> bingoNumber = Arrays.asList(
                "N40","N36",
                "B0","B6",
                "L41","l26","L99","L02","l22",
                "M44","M16",
                "I40","I36");

        List<String> gNum = new ArrayList<>();


        Stream<String> ioNumStream = Stream.of("A01","A02","A03","A12","A21","S33");
        Stream<String> inNumStream = Stream.of("S33","S39","S67","S88","S56","A21");
        Stream<String> concatStream = Stream.concat(ioNumStream,inNumStream);
        //System.out.println(concatStream.count());

        System.out.println();

        long uniqueCount = concatStream
            .distinct()
            //.peek(System.out::println)
            .count();

        //peek method is used for debugging
        System.out.print("Count total string values that are unique: " + uniqueCount);
        System.out.println("\n");


        Employee jerry = new Employee("Jerry Dean", 29);
        Employee harold = new Employee("Harold Dean", 35);
        Employee harvey = new Employee("Harvey Brown", 30);
        Employee lon = new Employee("Lon Elwood", 41);
        Employee dorthy = new Employee("Frances Brown",22);


        Department hr = new Department("Human Resources");
        hr.addEmployee(jerry);
        hr.addEmployee(harold);
        hr.addEmployee(lon);

        Department accounting = new Department("Accounting");
        accounting.addEmployee(dorthy);
        accounting.addEmployee(harvey);

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);


        System.out.println("Display All Department Employees:");
        departments.stream()
                    .flatMap(department -> department.getEmployees().stream())
                    .forEach(System.out::println);
        System.out.println();


        System.out.println("Display Department Employees age less than 30:");
        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .filter(age -> age.getAge() < 30)
                .forEach(System.out::println);
        System.out.println();



        System.out.println("Display in uppercase and only 'L' bingo numbers");
        List<String> sortGnum = bingoNumber
                .stream()
                .map(String::toUpperCase)
                .filter(s->s.startsWith("L"))
                .sorted()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);


        for(String s: sortGnum) {
            System.out.println(s);
        }
        System.out.println();


        Map<Integer,List<Employee>> groupedByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy((employee -> employee.getAge())));


        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((e1,e2) -> e1.getAge() < e2.getAge() ? e1:e2)
                .ifPresent(System.out::println);
      }
    }
