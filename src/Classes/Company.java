/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import DataStructure.Data;
import Interfaces.DashboardApple;
import Interfaces.DashboardHP;
import java.util.concurrent.Semaphore;
import javax.swing.JFrame;

public class Company {
    private String company;
    private int totalEmployees;
    private int actualEmployees;
    private Employee[] employees;
    private Employee[] ePlacaBase;
    private Employee[] eCPU;
    private Employee[] eRAM;
    private Employee[] eFA;
    private Employee[] eTG;
    private Employee[] assemblers;
    private Storage[] storages;
    private int dayCount;
    private ProjectManager projectManager;
    private Director director;
    private int daysLeft;
//    private JFrame window;
  
    private static Storage storage;
    private Semaphore mutex;
    
    private float cost = 0;
    private float earning = 0;
    private float profit = 0;
    
    private int days = 0; 
  
    
    private int computerCount;
    private int graphicComputerCount;
    private int assemblerCount;
    
    private int totalComputers;
    private int totalGraphipComputers;
    
    DashboardApple dApple;
    DashboardHP dHP;
    

  
    public Company(String company, int totalEmployees, int daysLeft) {

        this.company = company;
        this.totalEmployees = totalEmployees;
        this.employees = new Employee[totalEmployees];
        this.storages = new Storage[5]; // 5 tipos de trabajadores
        this.mutex = new Semaphore(1);

        this.computerCount = 0;
        this.graphicComputerCount = 0;

        this.daysLeft = daysLeft; // Inicializa días restantes (puedes ajustar este valor)

//        if (company.equals("APPLE")) {
//        this.dApple = new DashboardApple(this, null);
//        } else if (company.equals("HP")) {
//            this.dHP = new DashboardHP(this, null);
//        }

        
        // Inicializar los almacenes con capacidades fijas
        for (int i = 0; i < 5; i++) {
            Storage storage = new Storage(Data.storageCapacities[i], Data.producerTypes[i], this);
            storage.getCapacity();

            this.storages[i] = storage;
        }


        this.projectManager = new ProjectManager(this.company, this.mutex, Data.dayDuration, this.daysLeft);
        this.director = new Director(this.company, this.mutex, this.projectManager);

    }

    public void distributeEmployees() {
        // Distribuir el total de empleados de la compañía entre los 5 tipos de trabajadores y los ensambladores de las computadoras
        int[] employeeCounts = new int[6]; // Contadores de empleados por tipo

        // Asignar un trabajador de cada tipo como mínimo
        for (int i = 0; i < 6; i++) {
            employeeCounts[i] = 1;
        }

        // Distribuir los empleados restantes
        int remainingEmployees = this.totalEmployees - 6;
        for (int i = 0; i < remainingEmployees; i++) {
            int type = (int) (Math.random() * 6);
            employeeCounts[type]++;
        }

        // Crear los empleados
        int employeeIndex = 0;
        for (int i = 0; i < 6; i++) {
            Storage storage = (i < 5) ? this.storages[i] : null;
            for (int j = 0; j < employeeCounts[i]; j++) {
                Employee employee = new Employee(this, employeeIndex, i, 0, 0, storage, new Semaphore(1));
                this.employees[employeeIndex] = employee;
                employeeIndex++;
            }
        }
    }
    
    
    public void startWork() {
       
        for (Employee employee : employees) {
            employee.start();
        }
        
        this.projectManager.start();
        this.director.start();
        System.out.println("Project started for " + this.company);
    }

    public void updateDaysLeft(int days) {
        this.daysLeft = days; // Actualiza los días restantes
        this.projectManager.setDaysLeft(days); // Sincroniza el Project Manager con los nuevos días
    }


    
    public void incrementComputerCount() {
    this.computerCount++;
    System.out.println("\n\n" + this.company + " Computadoras normales " + this.computerCount + "\n");
    this.updateDashboard();
   
}   

public void incrementGraphicComputerCount() {
    this.graphicComputerCount++;
    System.out.println("\n\n" + this.company + " Computadoras con gráficas " + this.getGraphicComputerCount() + "\n");
    this.updateDashboard();
   
}
    
    public void incrementAssemblerCount() {
        this.assemblerCount++;
       
    }
    
    
    public void updateDashboard() {
        if (this.company.equals("APPLE")) {
            if (dApple != null) {
                dApple.updateComputerCount(this.getComputerCount());
                dApple.updateGraphicComputerCount(this.getGraphicComputerCount());

            }
        } else {
            if (dHP != null) {
                dHP.updateComputerCount(this.getComputerCount());
                dHP.updateGraphicComputerCount(this.getGraphicComputerCount());

            }
        }
    }
  
   // Método para actualizar el dashboard con los contadores de almacenamiento
public void updateStorageCapacity() {
    if (this.company.equals("APPLE")) {
        if (dApple != null) {
            for (int i = 0; i < storages.length; i++) {
                int currentCapacity = storages[i].getCurrentCapacity();
                int maxCapacity = storages[i].getCapacity();
                if (i == 0) {
                    dApple.updatePlacaBaseCount(currentCapacity);
//                    dApple.getEpb().setText(currentCapacity + " / " + maxCapacity); // Placa Base
                } else if (i == 1) {
                    dApple.updateCPUCount(currentCapacity);
//                    dApple.getEc().setText(currentCapacity + " / " + maxCapacity); // CPU
                } else if (i == 2) {
                    dApple.updateRAMCount(currentCapacity);
//                    dApple.getEr().setText(currentCapacity + " / " + maxCapacity); // Memoria RAM
                } else if (i == 3) {
                    dApple.updateFuenteAlimentacionCount(currentCapacity);
//                    dApple.getEf().setText(currentCapacity + " / " + maxCapacity); // Fuente de Alimentación
                } else if (i == 4) {
                    dApple.updateTarjetasGraficasCount(currentCapacity);
//                    dApple.getEt().setText(currentCapacity + " / " + maxCapacity); // Tarjetas Gráficas
                }
            }
        }
    } else if (this.company.equals("HP")) {
        if (dHP != null) {
            for (int i = 0; i < storages.length; i++) {
                int currentCapacity = storages[i].getCurrentCapacity();
                int maxCapacity = storages[i].getCapacity();
                if (i == 0) {
                    dHP.updatePlacaBaseCount(currentCapacity);
//                    dApple.getEpb().setText(currentCapacity + " / " + maxCapacity); // Placa Base
                } else if (i == 1) {
                    dHP.updateCPUCount(currentCapacity);
//                    dApple.getEc().setText(currentCapacity + " / " + maxCapacity); // CPU
                } else if (i == 2) {
                    dHP.updateRAMCount(currentCapacity);
//                    dApple.getEr().setText(currentCapacity + " / " + maxCapacity); // Memoria RAM
                } else if (i == 3) {
                    dHP.updateFuenteAlimentacionCount(currentCapacity);
//                    dApple.getEf().setText(currentCapacity + " / " + maxCapacity); // Fuente de Alimentación
                } else if (i == 4) {
                    dHP.updateTarjetasGraficasCount(currentCapacity);
//                    dApple.getEt().setText(currentCapacity + " / " + maxCapacity); // Tarjetas Gráficas
                }
            }
        }
    }
}

// Método para actualizar el número de computadoras ensambladas y computadoras con gráficas
public void updateComputerCounts() {
    if (this.company.equals("APPLE")) {
        if (dApple != null) {
            dApple.updateComputerCount(this.getComputerCount());
            dApple.updateGraphicComputerCount(this.getGraphicComputerCount());
        }
    } else if (this.company.equals("HP")) {
        if (dHP != null) {
            dHP.updateComputerCount(this.getComputerCount());
            dHP.updateGraphicComputerCount(this.getGraphicComputerCount());
        }
    }
}
    
  
   public void adjustEmployeeCount(int type, int amount) {
    if (type < 0 || type >= 6) {
        System.out.println("Tipo de empleado inválido");
        return;
    }

    int currentCount = 0;
    for (Employee employee : employees) {
        if (employee != null && employee.getType() == type) {
            currentCount++;
        }
    }

    int newCount = currentCount + amount;

    if (newCount < 1) {
        System.out.println("No se puede tener menos de 1 empleado en un tipo de empleado");
        return;
    }

    int totalEmployeesCount = 0;
    for (Employee employee : employees) {
        if (employee != null) {
            totalEmployeesCount++;
        }
    }

    if (totalEmployeesCount + amount > totalEmployees) {
        System.out.println("No se puede superar el máximo de empleados en la compañía");
        return;
    }

    if (amount > 0) {
        // Agregar empleados
        for (int i = 0; i < amount; i++) {
            Storage storage = (type < 5) ? storages[type] : null;
            Employee employee = new Employee(this, employees.length, type, 0, 0, storage, new Semaphore(1));
            // Buscar el primer espacio disponible en el arreglo
            int index = 0;
            while (index < employees.length && employees[index] != null) {
                index++;
            }
            if (index < employees.length) {
                employees[index] = employee;
                employee.start();
            } else {
                // Si no hay espacio disponible, aumentar el tamaño del arreglo
                Employee[] newEmployees = new Employee[employees.length + 1];
                System.arraycopy(employees, 0, newEmployees, 0, employees.length);
                newEmployees[employees.length] = employee;
                employees = newEmployees;
                employee.start();
            }
        }
    } else if (amount < 0) {
        // Restar empleados
        int count = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getType() == type) {
                employees[i].interrupt();
                employees[i] = null;
                count++;
                if (count == -amount) {
                    break;
                }
            }
        }
        // Reorganizar el arreglo para eliminar valores null
        int newIndex = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                employees[newIndex] = employees[i];
                newIndex++;
            }
        }
        // Reducir el tamaño del arreglo si es necesario
        if (newIndex < employees.length) {
            Employee[] newEmployees = new Employee[newIndex];
            System.arraycopy(employees, 0, newEmployees, 0, newIndex);
            employees = newEmployees;
        }
    }

    printEmployeeCount(type);
}
  
    public void printEmployeeCount(int type) {
    int count = 0;
    if (employees != null) {
        for (Employee employee : employees) {
            if (employee != null && employee.getType() == type) {
                count++;
            }
        }
    }
    System.out.println("Hay " + count + " empleados de tipo " + type + " actualmente.");
}
  
    
    public int getComputerCount() {
        return this.computerCount;
    }
    
       public String getCompany() {
        return company;
    }
       
       public void setComputerCount(int count) {
        computerCount = count;
    }
    
    
    
    
  public int getEmployeeCount(int type) {
    int count = 0;
    for (Employee employee : employees) {
        if (employee != null && employee.getType() == type) {
            count++;
        }
    }
    return count;
}



   
    public Storage[] getStorages() {
        return this.storages;
    }

    public Employee[] getEmployees() {
        return this.employees;
    }
    
    public ProjectManager getProjectManager() {
        return projectManager;
    }

    public int getDaysLeft() {
        return daysLeft; // Getter para días restantes
    }

    public void printEmployeeCounts() {
    int[] employeeCounts = new int[6]; // Contadores de empleados por tipo

    // Contar los empleados por tipo
    for (Employee employee : this.employees) {
        employeeCounts[employee.getType()]++;
    }

    // Imprimir los contadores
        System.out.println("\n");
    for (int i = 0; i < employeeCounts.length; i++) {
        System.out.println("Tipo " + i + ": " + employeeCounts[i] + " empleados");
    }
}

    /**
     * @return the graphicComputerCount
     */
    public int getGraphicComputerCount() {
        return graphicComputerCount;
    }

    /**
     * @return the assemblerCount
     */
    public int getAssemblerCount() {
        return assemblerCount;
    }

    /**
     * @param assemblerCount the assemblerCount to set
     */
    public void setAssemblerCount(int assemblerCount) {
        this.assemblerCount = assemblerCount;
    }
    
    /**
     * @return the DashboardApple interface
     */
    public DashboardApple getWindowApple() {
        return this.dApple;
    }
    
    /**
     * @param dApple the DashboardApple interface
     */
    public void setWindowApple(DashboardApple dApple) {
        this.dApple = dApple;
    }
    
    /**
     * @return the DashboardApple interface
     */
    public DashboardHP getWindowHP() {
        return this.dHP;
    }
    
    /**
     * @param dApple the DashboardApple interface
     */
    public void setWindowHP(DashboardHP dHP) {
        this.dHP = dHP;
    }
    
    public void printApple() {
        this.getWindowApple().getEpb2().setText("AQUIIIII");
    }
    
}

