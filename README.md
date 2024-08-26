# Sensor Mock Clients

This repository contains two UDP-based clients: `HumidityUdpClient` and `TemperatureUdpClient`. These clients simulate sensor data being sent to a server in parallel.

## Table of Contents

- [Requirements](#requirements)
- [Project Structure](#project-structure)
- [Setup](#setup)
- [Running the Clients](#running-the-clients)
- [Customizing Sensor Data](#customizing-sensor-data)

## Requirements

Before running the clients, ensure you have the following installed:

- Java 8+
- Maven

## Project Structure

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.warehouse
│   │   │       ├── HumidityUdpClient.java
│   │   │       └── TemperatureUdpClient.java
│   │   └── resources
│   │       ├── humidity_input.txt
│   │       └── temperature_input.txt
└── pom.xml
```

- **HumidityUdpClient.java**: Sends simulated humidity data over UDP.
- **TemperatureUdpClient.java**: Sends simulated temperature data over UDP.
- **humidity_input.txt**: Contains the messages to be sent by `HumidityUdpClient`.
- **temperature_input.txt**: Contains the messages to be sent by `TemperatureUdpClient`.

## Setup

1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/sensor-mock-clients.git
    cd sensor-mock-clients
    ```

2. **Build the project using Maven**:
    ```bash
    mvn clean install
    ```

## Running the Clients

### Running Humidity Client

To run the `HumidityUdpClient`, you can use the following Maven command:

```bash
mvn exec:java -Dexec.mainClass="com.warehouse.HumidityUdpClient"
```

This will start the client and send humidity sensor data to the configured UDP server.

### Running Temperature Client

To run the `TemperatureUdpClient`, use this Maven command:

```bash
mvn exec:java -Dexec.mainClass="com.warehouse.TemperatureUdpClient"
```

This will start the client and send temperature sensor data to the configured UDP server.

### Running Clients in Parallel

To run both clients in parallel, you can either open two terminal windows or use a tool like `tmux` or `screen` to split the terminal.

**Example using two terminal windows**:

1. In the first terminal, run:
    ```bash
    mvn exec:java -Dexec.mainClass="com.warehouse.HumidityUdpClient"
    ```

2. In the second terminal, run:
    ```bash
    mvn exec:java -Dexec.mainClass="com.warehouse.TemperatureUdpClient"
    ```

Both clients will run concurrently, sending their respective sensor data in parallel.

## Customizing Sensor Data

You can customize the sensor data by editing the `humidity_input.txt` and `temperature_input.txt` files located in the `src/main/resources` directory. These files contain the messages that will be sent by the clients.

**Example of `humidity_input.txt`**:
```
sensor_id=h1; value=45
sensor_id=h2; value=50
```

**Example of `temperature_input.txt`**:
```
sensor_id=t1; value=30
sensor_id=t2; value=32
```

Each client will read the messages from these files and send them in a loop. Modify the values to simulate different sensor readings.

---

Feel free to further modify the README according to your specific needs or project structure.
