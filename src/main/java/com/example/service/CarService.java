package com.example.service;

import com.example.domain.Car;
import com.example.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;


    public void testCars(){

        Car car1= new Car(1L,"Jaguar","TheRoadIsYours",2016,"1234ENG", 61405.00);
        carRepository.save(car1);

        Car car2= new Car (2L,"BMW","LoveAtFirstDrive",2014,"9065AME", 75399.00);
        carRepository.save(car2);

        Car car3= new Car (3L,"Lexus","LC500",2016,"5638DDD",85500.00);
        carRepository.save(car3);

        Car car4= new Car (4L,"Aston Martin","DB10",2015,"3564GER", 3156825.00);
        carRepository.save(car4);

        Car car5= new Car (5L,"RomeoAlfaJulietta","DramaMasterpiece",2013,"4749JAV",66200.00);
        carRepository.save(car5);

        Car car6= new Car (6L,"BMW","LoveAtFirstDrive",2014,"9065AND", 75399.00);
        carRepository.save(car6);

        Car car7= new Car(7L,"Jaguar","FPACE",2015,"1235ENG",45000.00 );
        carRepository.save(car7);

        Car car8= new Car (8L,"BMW","CABRIO",2014,"9065AME", 47550.00);
        carRepository.save(car8);

        Car car9= new Car(9L,"Jaguar","FPACE",2015,"5321ENG",45000.00 );
        carRepository.save(car9);


        System.out.println("Cars made since 2016 ");
        System.out.println(carRepository.findByYearOfFabGreaterThanEqual(2016));

        System.out.println("Cars made before 2014 ");
        System.out.println(carRepository.findByYearOfFabLessThan(2016));


        System.out.println("Cars made between 2014 and 2016");
        System.out.println(carRepository.findByYearOfFabBetween(2014,2016));

        System.out.println("Cars that cost more than 25000 Euros");
        System.out.println(carRepository.findByPriceGreaterThanEqual(25000.00));

        System.out.println("Cars that cost between 50000 and 6000000 Euros ");
        System.out.println(carRepository.findByPriceBetween(50000.00,100000.00));

        System.out.println("Car with plate number 3564GER ");
        System.out.println(carRepository.findByPlateNumber("3564GER"));

        System.out.println("Cars of brand Jaguar and model FPACE");
        System.out.println(carRepository.findByBrandAndModel("Jaguar","FPACE"));

        System.out.println("Cars of brand BMW and price less than 80000 Euros");
        System.out.println(carRepository.findByBrandAndPrice("BMW",80000.00));

        System.out.println("Cars of brand Aston Martin, model of James Bond of the movie Spectrum,DB10, that costs more than 3100000 Euros ");
        System.out.println(carRepository.findByBrandAndModelAndPriceGreaterThanEqual("Aston Martin","DB10",3100000.00));


        System.out.println("List of Jaguar brand cars");
        System.out.println(carRepository.findByBrand("Jaguar"));

        System.out.println("The average price all of Lexus cars is: " +carRepository.findAveragePerBrand("Lexus"));


        //Querys con JPQL

        System.out.println("All cars which plate number contains 'ENG': " +carRepository.findCarByPlateNumberSubstring("ENG"));

        System.out.println("All cars wich plate number contains letter 'E' " +carRepository.findByPlateNumberContains("E"));

        System.out.println("Statistics");
        showStatistics(carRepository.AvgAndMaxAndMinPricesPerBrand());

        System.out.println("Statistics sorted by AvgPrice descendant");
        showStatistics(carRepository.AvgAndMaxAndMinPricesPerBrandOrderedByAVGPrice());


        System.out.println("Number of cars made each year: ");

        List<Object[]> carList = carRepository.CarsMadeByYear();

        for (Object[] car : carList)
        {
            Integer year = (Integer)car[0];
            System.out.println("Year: "+car[0]);
            System.out.println("Quantity: "+ car[1]);
            System.out.println("List of cars: "+carRepository.findByYearOfFab(year)+System.lineSeparator());
        }


    }

    private void showStatistics(List<Object[]> statisticsList) {
        for ( Object[] statistic: statisticsList)
        {
            System.out.println("Brand: "+statistic[0]);
            System.out.println("AVG = "+statistic[1] +" euros");
            System.out.println("MIN = "+statistic[2]+" euros");
            System.out.println("MAX = "+statistic[3]+" euros"+System.lineSeparator());
        }

        // el objeto es cada una de las líneas de la tabla que muestra al hacer la query. statistic[] son las columnas
    }
}
