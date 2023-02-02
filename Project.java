import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 


public class Project {
	private static ArrayList<User> users = new ArrayList<User>();
	private static ArrayList<SponsorEntry> sponsorEntries = new ArrayList<SponsorEntry>();
	private static ArrayList<UserTask> userTasks = new ArrayList<UserTask>();
	private static ArrayList<CompanyTask> companyTasks = new ArrayList<CompanyTask>();
	private static User currentUser;
	
	public static User searchUser() {
		Scanner scan = new Scanner(System.in);
		User tmp = new User();
		boolean userFound = false;
		String username;
		do {
			System.out.println("Geben Sie den Benutzernamen ein oder '0' um die Suche zu beenden\nEingabe:");
			username = scan.nextLine();
			for(int i = 0; i < users.size(); i++) {
				if(users.get(i).getUsername().equals(username)) {
					tmp = users.get(i);
					userFound = true;
				}
			}
			if(!userFound && !username.equals("0")) {
				System.out.println("Benutzername wurde nicht gefunden");
			}
		}while(!userFound && !username.equals("0"));
		if(username.equals("0")) {
			return null;
		}
		return tmp;
	}
	
	public static SponsorEntry searchSponsorEntry(User u) {
		Scanner scan = new Scanner(System.in);
		SponsorEntry tmp = new SponsorEntry();
		boolean sponsorEntryFound = false;
		int id;
		do {
			System.out.println("Geben Sie die Sponsoreintrag-Id ein oder '0' um die Suche zu beenden\nEingabe:");
			id = scan.nextInt();
			for(int i = 0; i < sponsorEntries.size(); i++) {
				if(sponsorEntries.get(i).getId() == id) {
					tmp = sponsorEntries.get(i);
					sponsorEntryFound = true;
				}
			}
			if(!sponsorEntryFound && id != 0) {
				System.out.println("Sponsoreintrag wurde nicht gefunden");
			}
		}while(!sponsorEntryFound && id != 0);	
		if(id == 0) {
			tmp.setId(0);
			return tmp;
		}
		return tmp;
	}
	
	public static UserTask searchUserTask() {
		Scanner scan = new Scanner(System.in);
		UserTask tmp = new UserTask();
		boolean taskFound = false;
		int id;
		do {
			System.out.println("Geben Sie die Task-Id ein oder '0' um die Suche zu beenden\nEingabe:");
			id = scan.nextInt();
			for(int i = 0; i < userTasks.size(); i++) {
				if(userTasks.get(i).getId() == id) {
					tmp = userTasks.get(i);
					taskFound = true;
				}
			}
			if(!taskFound && id != 0) {
				System.out.println("Aufgabe wurde nicht gefunden");
			}
		}while(!taskFound && id != 0);	
		if(id == 0) {
			tmp.setId(0);
			return tmp;
		}
		return tmp;
	}
	
	public static CompanyTask searchCompanyTask() {
		Scanner scan = new Scanner(System.in);
		CompanyTask tmp = new CompanyTask();
		boolean taskFound = false;
		int id;
		do {
			System.out.println("Geben Sie die Task-Id ein oder '0' um die Suche zu beenden\nEingabe:");
			id = scan.nextInt();
			for(int i = 0; i < companyTasks.size(); i++) {
				if(companyTasks.get(i).getId() == id) {
					tmp = companyTasks.get(i);
					taskFound = true;
				}
			}
			if(!taskFound && id != 0) {
				System.out.println("Firmenbezogene Aufgabe wurde nicht gefunden");
			}
		}while(!taskFound && id != 0);	
		if(id == 0) {
			tmp.setId(0);
			return tmp;
		}
		return tmp;
	}
	
	public static boolean checkPassword(User tmp) {
		Scanner scan = new Scanner(System.in);
		boolean correct = false;
		String password = "";
		for(int i = 0; i < 5 & !correct & !password.equals("exit"); i++) {
			System.out.println("Geben Sie das Passwort ein oder 'exit' um die Eingabe zu beenden\nEingabe:");
			password = scan.nextLine();
			if(tmp.getPassword().equals(password)) {
				correct = true;
			}else if (password.equals("exit")){
				System.out.println("");
			}else {
				System.out.println("Falsche Eingabe\nVersuche uebrig: " + (5-i-1) + "\n");
			}
		}
		return correct;
	}
	
	public static User logIn() {
		Scanner scan = new Scanner(System.in);
		User tmp = new User();
		boolean correct = false;
		String password;
		
		do{
			do{
				tmp = searchUser();
			}while(tmp == null);
			if(tmp.getPassword().equals("Password")) {
				tmp.resetPassword();
			}
			if(!(tmp.getId() == 0)) {
				correct = checkPassword(tmp);
			}
		}while(!correct && !tmp.equals(null));
		System.out.println("\nWillkommen " + tmp.getUsername());
		return tmp;
	}
	
	public static void printUsers(){
		System.out.println("");
		for (int i = 0; i <users.size(); i++) {
			users.get(i).printUser();
		}
	}
	
	public static void printAllTasks() {
		for(int i = 0; i < userTasks.size(); i++) {
			userTasks.get(i).printUserTask();
		}
		writeLogDatei("hat alle Aufgaben ausgegeben");
	}
	
	public static void printAllCompanyTasks() {
		for(int i = 0; i < companyTasks.size(); i++) {
			companyTasks.get(i).printTask();
		}
	}
	
	public static void printAllSponsorEntries() {
		for(int i = 0; i < sponsorEntries.size(); i++) {
			sponsorEntries.get(i).printSponsorEntry();
		}
	}
	
	public static float calculateAlreadyPaidCosts(){
		float sum = 0; 
		for(int i = 0; i < userTasks.size(); i++) {
			if(userTasks.get(i).getCosts().isAlreadyPaid()){
				sum += userTasks.get(i).getCosts().getAmount();
			}
		}
		for(int i = 0; i < companyTasks.size(); i++) {
			if(companyTasks.get(i).getCosts().isAlreadyPaid()){
				sum += companyTasks.get(i).getCosts().getAmount();
			}
		}
		return sum;
	}
	
	public static float calculateUpcomingCosts(){
		float sum = 0; 
		for(int i = 0; i < userTasks.size(); i++) {
			if(!userTasks.get(i).getCosts().isAlreadyPaid()){
				sum += userTasks.get(i).getCosts().getAmount();
			}
		}
		for(int i = 0; i < companyTasks.size(); i++) {
			if(!companyTasks.get(i).getCosts().isAlreadyPaid()){
				sum += companyTasks.get(i).getCosts().getAmount();
			}
		}
		return sum;
	}
	
	public static float calculateIncome(){
		float sum = 0; 
		for(int i = 0; i < sponsorEntries.size(); i++) {
			sum += sponsorEntries.get(i).getAmount();
		}
		return sum;
	}
	
	public static float calculateTotal() {
		return calculateIncome() - calculateUpcomingCosts() - calculateAlreadyPaidCosts();
	}
	
	public static void printProject() {
		System.out.println("Bereitsbezahlte Kosten: " + calculateAlreadyPaidCosts() + "\n"
				+ "Bevorstehende Kosten: " + calculateUpcomingCosts() + "\n"
				+ "Einnahmen: " + calculateIncome() + "\n\n"
				+ "Gesamt: " + calculateTotal() + "\n");
	}
	
	public static void fillFiles() {
		try {
			FileWriter sponsorEntryFileWriter = new FileWriter("SponsorEntryFile.csv");
			FileWriter userFileWriter = new FileWriter("UserFile.csv");
			FileWriter userTaskFileWriter = new FileWriter("UserTaskFile.csv");
			FileWriter companyTaskFileWriter = new FileWriter("CompanyTaskFile.csv");
			
			BufferedWriter writeFile = null;
			
			writeFile = new BufferedWriter(sponsorEntryFileWriter);
			
			for(int i = 0; i < sponsorEntries.size(); i++) {
				writeFile.write(sponsorEntries.get(i).toString());
				writeFile.newLine();
			}
			 
			writeFile.close();
			
			writeFile = new BufferedWriter(userFileWriter);
			
			for(int i = 0; i < users.size(); i++) {
				if(users.get(i) instanceof Admin) {
					writeFile.write("Admin");
				}else {
					writeFile.write("User");
				}
				writeFile.append(";");
				writeFile.write(users.get(i).toString());
				writeFile.newLine();
			}
			
			writeFile.close();
			
			writeFile = new BufferedWriter(userTaskFileWriter);
			
			for(int i = 0; i < userTasks.size(); i++) {	
				writeFile.write(userTasks.get(i).toString());
				writeFile.newLine();
			}
			
			writeFile.close();
			
			writeFile = new BufferedWriter(companyTaskFileWriter);
			
			for(int i = 0; i < companyTasks.size(); i++) {	
				writeFile.write(companyTasks.get(i).toString());
				writeFile.newLine();
			}
			
			writeFile.close();
	
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadData() {
		try {
			FileReader sponsorEntryReader = new FileReader("SponsorEntryFile.csv");
			FileReader userReader = new FileReader("UserFile.csv");
			FileReader userTaskReader = new FileReader("UserTaskFile.csv");
			FileReader companyTaskReader = new FileReader("CompanyTaskFile.csv");
			
			String readString;
			String data[] = null;
			
			BufferedReader readFile = null;
			
			readFile = new BufferedReader(userReader);
			while((readString = readFile.readLine()) != null) {
				data = readString.split(";");
				int id = Integer.valueOf(data[5]);
				if(data[0].equals("Admin")) {
					Admin admin = new Admin(data[1], data[2], data[3], data[4]);
					admin.setId(id);
					users.add(admin);
				}else {
					User user = new User(data[1], data[2], data[3], data[4]);
					user.setId(id);
					users.add(user);
				}				
				
			}
			
			readFile.close();			
			
			readFile = new BufferedReader(sponsorEntryReader);
			while((readString = readFile.readLine()) != null) {
				data = readString.split(";");
				Company c = new Company(data[1], data[2], data[3], data[4]);
				User u = new User(data[5], data[6], data[7], data[8]);
				int id = Integer.valueOf(data[0]);
				float amount = Float.valueOf(data[9]);
				int position = 0;
				for(int i = 0; i < users.size(); i++) {
					if(users.get(i).getId() == u.getId()) {
						position = i;
					}
				}
				SponsorEntry sponsorData = new SponsorEntry(id, c, users.get(position), amount);
				sponsorEntries.add(sponsorData);
				sponsorData.getUser().getSponsorEntries().add(sponsorData);
			}

			readFile.close();
			
			readFile = new BufferedReader(userTaskReader);
			while((readString = readFile.readLine()) != null) {
				data = readString.split(";");
				int id = Integer.valueOf(data[0]);
				Costs c = new Costs(Float.valueOf(data[4]), Boolean.valueOf(data[5]));
				User u = new User(data[6], data[7], data[8], data[9]);
				int position = 0;
				for(int i = 0; i < users.size(); i++) {
					if(users.get(i).getId() == u.getId()) {
						position = i;
					}
				}

				UserTask ut = new UserTask(data[1], data[2], Boolean.valueOf(data[3]), c, users.get(position));
				ut.setId(id);
				userTasks.add(ut);
				ut.getUser().getUserTasks().add(ut);
			}

			readFile.close();

			readFile = new BufferedReader(companyTaskReader);
			while((readString = readFile.readLine()) != null) {
				data = readString.split(";");
				int id = Integer.valueOf(data[0]);
				Costs cost = new Costs(Float.valueOf(data[4]), Boolean.valueOf(data[5]));
				Company comp = new Company(data[6], data[7], data[8], data[9]);

				CompanyTask ct = new CompanyTask(data[1], data[2], Boolean.valueOf(data[3]), cost, comp);
				ct.setId(id);

				companyTasks.add(ct);
			}

			readFile.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getTimeStamp() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		return now.toString();
	}
	
	public static void writeLogDatei(String message) {
		try {
		    FileWriter logDateiFileWriter = new FileWriter("LogDateiFile.csv",true);
		    if(currentUser == null) {
		    	logDateiFileWriter.write("[" + getTimeStamp() + "]: " + message + "\n");
		    }else {
		    	logDateiFileWriter.write("[" + getTimeStamp() + "]: " + currentUser.toPrintString() + " "+ message + "\n");
		    }
		    logDateiFileWriter.close();
	
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String []args) {
		Scanner scan = new Scanner(System.in);
		
		writeLogDatei("Das Programm wurde gestartet");
		
		loadData();
		
		boolean finished = false;

		while(finished == false) {
			if(users.size() == 0) {
				System.out.println("Willkommen!\nDrücken Sie [Enter] um das Programm zu starten:");
				scan.nextLine();
				System.out.println("Geben Sie ihre Daten ein:");
				currentUser = Admin.creatUser();
				writeLogDatei("hat seinen Benutzer und ein Projekt erstellt");
			}
			currentUser = logIn();
			writeLogDatei("hat sich angemeldet");
			int auswahl;
			int auswahl2;
			do {
				printProject();
				System.out.println("Was moechten Sie tun?\n"
						+ "\t1) Aufgaben\n"
						+ "\t2) Firmenbezogene Aufgaben\n"
						+ "\t3) Sponsoreintraege\n"
						+ "\t4) Passwort");
				if (currentUser instanceof Admin) {
					System.out.println("\t5) Benutzer");
				}
				System.out.println("\t0) Ausloggen\n"
						+ "\t-1) Programm beenden\n"
						+ "Eingabe: ");
				auswahl = scan.nextInt();
				switch(auswahl) {
					case -1: finished = true; break;
					case 1:
						do {
							System.out.println("Was moechten Sie tun?\n"
									+ "\t1) Meine Aufgaben ausgeben");
							if (currentUser instanceof Admin) {
								System.out.println(
										"\t2) Aufgaben eines Benutzers ausgeben\n"
										+ "\t3) Aufgaben aller Benutzer ausgeben\n"
										+ "\t4) Aufgabe erstellen\n"
										+ "\t5) Aufgabe bearbeiten\n"
										+ "\t6) Aufgabe loeschen");
							}
							System.out.println("\t0) Exit\nEingabe: ");
							auswahl2 = scan.nextInt();

							if(currentUser instanceof Admin) {
								switch(auswahl2) {
									case 1: currentUser.printUserTasks(); break;
									case 2: searchUser().printUserTasks(); break;
									case 3: printAllTasks(); break;
									case 4: ((Admin) currentUser).addUserTask(); break;
									case 5: ((Admin) currentUser).editUserTask(Project.searchUserTask()); break;
									case 6: ((Admin) currentUser).deleteUserTask(); break;
									case 0: break;
									default: System.out.println("Falsche Eingabe");
								}									
							}else{
								switch(auswahl2) {
									case 1: currentUser.printUserTasks(); break;
									case 0: break;
									default: System.out.println("Falsche Eingabe");
								}
							}
						}while(auswahl2 != 0 && currentUser != null);
				break;
				case 2:
					do {
						System.out.println("Was moechten Sie tun?\n"
								+ "\t1) Firmenbezogene Aufgaben ausgeben");
						if (currentUser instanceof Admin) {
							System.out.println(
									"\t2) Firmenbezogene Aufgabe erstellen\n"
									+ "\t3) Firmenbezogene Aufgabe bearbeiten\n"
									+ "\t4) Firmenbezogene Aufgabe loeschen");
						}
						System.out.println("\t0) Exit\nEingabe: ");
						auswahl2 = scan.nextInt();
						if(currentUser instanceof Admin) {
							switch(auswahl2) {
								case 1: printAllCompanyTasks(); break;
								case 2: ((Admin) currentUser).addCompanyTask(); break;
								case 3: ((Admin) currentUser).editCompanyTask(Project.searchCompanyTask()); break;
								case 4: ((Admin) currentUser).deleteCompanyTask(); break;
								case 0: break;
								default: System.out.println("Falsche Eingabe");
							}
						}else{
							switch(auswahl2) {
								case 1: printAllCompanyTasks(); break;
								case 0: break;
								default: System.out.println("Falsche Eingabe");
							}
						}
					}while(auswahl2 != 0 && currentUser != null);
				break;
				case 3:
					do {
						if (currentUser instanceof Admin) {
							System.out.println("Was moechten Sie tun?\n"
									+ "\t1) Meine Sponsoreintraege ausgeben\n"
									+ "\t2) Sponsoreintraege eines Benutzers ausgeben\n"
									+ "\t3) Sponsoreintraege aller Benutzer ausgeben\n"
									+ "\t4) Sponsoreintrag erstellen\n"
									+ "\t5) Sponsoreintrag bearbeiten\n"
									+ "\t6) Sponsoreintrag loeschen");
						}else {
							System.out.println("Was moechten Sie tun?\n"
									+ "\t1) Sponsoreintraege ausgeben\n"
									+ "\t2) Sponsoreintrag erstellen\n"
									+ "\t3) Sponsoreintrag bearbeiten\n"
									+ "\t4) Sponsoreintrag loeschen");								
						}
						System.out.println("\t0) Exit\nEingabe: ");
						auswahl2 = scan.nextInt();
						if(currentUser instanceof Admin) {
							switch(auswahl2) {
								case 1: currentUser.printSponsorEntries(); break;
								case 2: searchUser().printSponsorEntries(); break;
								case 3: printAllSponsorEntries(); break;
								case 4: currentUser.addSponsorEntry(); break;
								case 5: currentUser.editSponsorEntry(Project.searchSponsorEntry(currentUser));
								case 6: currentUser.deleteSponsorEntry(); break;
								case 0: break;
								default: System.out.println("Falsche Eingabe");
							}
						}else{
							switch(auswahl2) {
								case 1: currentUser.printSponsorEntries(); break;
								case 2: currentUser.addSponsorEntry(); break;
								case 3: currentUser.editSponsorEntry(Project.searchSponsorEntry(currentUser));
								case 4: currentUser.deleteSponsorEntry(); break;
								case 0: break;
								default: System.out.println("Falsche Eingabe");
							}
						}
					}while(auswahl2 != 0 && currentUser != null);
				break;
				case 4:
					do {
						System.out.println("Was moechten Sie tun?\n"
								+ "\t1) Passwort zuruecksetzen");
						if (currentUser instanceof Admin) {
							
						}
						System.out.println("\t0) Exit\nEingabe: ");
						auswahl2 = scan.nextInt();
						if(currentUser instanceof Admin) {
							switch(auswahl2) {
								case 1: currentUser.resetPassword(); break;
								case 0: break;
								default: System.out.println("Falsche Eingabe");
							}
						}else{
							switch(auswahl2) {
								case 1: currentUser.resetPassword(); break;
								case 0: break;
								default: System.out.println("Falsche Eingabe");
							}
						}
					}while(auswahl2 != 0 && currentUser != null);
				break;
			}
			if(currentUser instanceof Admin) {
				switch(auswahl) {
					case 5:
						do {
							System.out.println(""
									+ "\t1) Alle Benutzer ausgeben\n"
									+ "\t2) Benutzer erstellen\n"
									+ "\t3) Benutzer bearbeiten\n"
									+ "\t4) Benutzer loeschen\n"
									+ "\t0) Exit\nEingabe: ");
							auswahl2 = scan.nextInt();
							switch(auswahl2) {
								case 1: printUsers(); break;
								case 2: ((Admin) currentUser).creatUser(); break;
								case 3: ((Admin) currentUser).editUser(searchUser()); break;
								case 4: Admin.deleteUser(Project.searchUser()); break;
								case 0: break;
								default: System.out.println("Falsche Eingabe"); break;
							}
						}while(auswahl2 != 0 && currentUser != null);
					break;
				}					
			}
		}while(auswahl != 0 && currentUser != null && finished == false);
	}
		
		fillFiles();
		
	}

	public static ArrayList<User> getUsers() {
		return users;
	}

	public static void setUsers(ArrayList<User> users) {
		Project.users = users;
	}

	public static ArrayList<SponsorEntry> getSponsorEntries() {
		return sponsorEntries;
	}

	public static void setSponsorEntries(ArrayList<SponsorEntry> sponsorEntries) {
		Project.sponsorEntries = sponsorEntries;
	}

	public static ArrayList<UserTask> getUserTasks() {
		return userTasks;
	}

	public static void setUserTasks(ArrayList<UserTask> userTasks) {
		Project.userTasks = userTasks;
	}

	public static ArrayList<CompanyTask> getCompanyTasks() {
		return companyTasks;
	}

	public static void setCompanyTasks(ArrayList<CompanyTask> companyTask) {
		Project.companyTasks = companyTask;
	}

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		Project.currentUser = currentUser;
	}
	
}