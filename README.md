### **Spring boot webFlux POC cool !**



#### **_*DOA class will act as publisher  & Browser will act as subscriber*_**



    return IntStream.rangeClosed(1,50)
                    .peek(i->System.out.println("processing count :"+i))
                    .mapToObj(i->new Customer(i,"customer  :"+i))
                    .collect(Collectors.toList());

**Generating 50 Customer Objects** 
