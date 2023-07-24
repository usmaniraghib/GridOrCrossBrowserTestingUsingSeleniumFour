# GridOrCrossBrowserTestingUsingSeleniumFour

#GRID

Check Grid Status before starting it :-
NON UI Version : http://localhost:4444/status
UI Version : http://localhost:4444

#Types of Grid :-
*******************************************Standalone************************************************************
1) STANDALONE MODE -> If we want to run "HUB" and "NODE" in same machine, we can use this mode.

*) To run grid in standalone mode follow the below steps;

STEP-1

Create one empty folder any where in your machine. Example:- C:\Grid_Jars\

Keep all the below file into this location C:\Grid_Jars\

selenium-server-4.9.1

STEP-2

Open the command prompt and run the below command.

C:\Grid_Jars> java -jar selenium-server-4.9.1.jar standalone --selenium-manager true

NOTE - 
--selenium-manager true

When you use above statement then no need download and pasted browser driver into the folder called "C:\Grid_Jars\".

selenium-manager will provide the browser driver based on the browser installed on your machine.
 
STEP-3

Run your testng.xml file

*******************************************Hub and Node************************************************************
2) HUB & NODE MODE -> 

*) When Node and Hub running separately on same machines

**) Hub
A Hub is composed by the following components: Router, Distributor, Session Map, New Session Queue, and Event Bus.

C:\Grid_Jars> java -jar selenium-server-4.9.1.jar hub

By default, the server will listen for RemoteWebDriver requests on http://localhost:4444.

**) Node
During startup time, the Node will detect the available drivers that it can use from the System PATH.

***) The command below assumes that Node is running on the same machine where the Hub is running.

C:\Grid_Jars> java -jar selenium-server-4.9.1.jar node --selenium-manager true

****) More than one Node on the same machine

Node 1
java -jar selenium-server-<version>.jar node --port 5555

Node 2
java -jar selenium-server-<version>.jar node --port 6666

*) Node and Hub on different machines

Hub and Nodes talk to each other via HTTP and the Event Bus (the Event Bus lives inside the Hub). 
A Node sends a message to the Hub via the Event Bus to start the registration process. 
When the Hub receives the message, reaches out to the Node via HTTP to confirm its existence.

To successfully register a Node to a Hub, it is important to expose the Event Bus ports (4442 and 4443 by default) on the Hub machine. 
This also applies for the Node port. With that, both Hub and Node will be able to communicate.

If the Hub is using the default ports, the --hub flag can be used to register the Node

java -jar selenium-server-<version>.jar node 
--hub http://<hub-ip>:4443

When the Hub is not using the default ports, the --publish-events and --subscribe-events flags are needed.
For example, if the Hub uses ports 8886, 8887, and 8888

java -jar selenium-server-<version>.jar hub 
--publish-events tcp://<hub-ip>:8886 
--subscribe-events tcp://<hub-ip>:8887 
--port 8888

The Node needs to use those ports to register successfully
java -jar selenium-server-<version>.jar node 
--publish-events tcp://<hub-ip>:8886 
--subscribe-events tcp://<hub-ip>:8887

*******************************************Distributed************************************************************
3) DISTRIBUTED
When using a Distributed Grid, each component is started separately, and ideally on different machines.

#################################
3.1) Event Bus: enables internal communication between different Grid components.
Default ports are: 4442, 4443, and 5557.

java -jar selenium-server-<version>.jar event-bus 
--publish-events tcp://<event-bus-ip>:4442 
--subscribe-events tcp://<event-bus-ip>:4443 
--port 5557

#################################
3.2) New Session Queue: adds new session requests to a queue, which will be queried by the Distributor
Default port is 5559.

java -jar selenium-server-<version>.jar sessionqueue --port 5559

#################################
3.3) Session Map: maps session IDs to the Node where the session is running
Default Session Map port is 5556. Session Map interacts with the Event Bus.

java -jar selenium-server-<version>.jar sessions 
--publish-events tcp://<event-bus-ip>:4442 
--subscribe-events tcp://<event-bus-ip>:4443 
--port 5556

#################################
3.4) Distributor: queries the New Session Queue for new session requests, and assigns them to a Node when the capabilities match. 
Nodes register to the Distributor the way they register to the Hub in a Hub/Node Grid.
Default Distributor port is 5553. 
Distributor interacts with New Session Queue, Session Map, Event Bus, and the Node(s).

java -jar selenium-server-<version>.jar distributor 
--publish-events tcp://<event-bus-ip>:4442 
--subscribe-events tcp://<event-bus-ip>:4443 
--sessions http://<sessions-ip>:5556 
--sessionqueue http://<new-session-queue-ip>:5559 
--port 5553 --bind-bus false

#################################
3.5) Router: redirects new session requests to the queue, and redirects running sessions requests to the Node running that session.
Default Router port is 4444. Router interacts with New Session Queue, Session Map, and Distributor.

java -jar selenium-server-<version>.jar router 
--sessions http://<sessions-ip>:5556 
--distributor http://<distributor-ip>:5553 
--sessionqueue http://<new-session-queue-ip>:5559 
--port 4444

#################################
3.6) Node(s):
Default Node port is 5555.
java -jar selenium-server-<version>.jar node 
--publish-events tcp://<event-bus-ip>:4442 
--subscribe-events tcp://<event-bus-ip>:4443

#################################

	public WebDriver driver;
	
	public WebDriver initializeBrowser(String browserName) throws MalformedURLException {		
		@SuppressWarnings("rawtypes")
		AbstractDriverOptions options = null;		
		if(browserName.equals("chrome")) {			
			options = new ChromeOptions();
			options.setCapability("browserVersion", "100");
			options.setCapability("platformName", "Windows");
			// Showing a test name instead of the session id in the Grid UI
			options.setCapability("se:name", "My simple test"); 
			// Other type of metadata can be seen in the Grid UI by clicking on the 
			// session info or via GraphQL
			options.setCapability("se:sampleMetadata", "Sample metadata value");			
		} else if(browserName.equals("microsoft-edge")) {			
			options = new EdgeOptions();
			options.setCapability("browserName", "MicrosoftEdge");			
		} else if(browserName.equals("firefox")) {			
			options = new FirefoxOptions();
			options.setCapability("browserName", "firefox");			
		}		
		driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);		
		driver.manage().window().maximize();		
		return driver;		
	}
	
	initializeBrowser();
	driver.get("http://www.google.com");
	driver.quit();
