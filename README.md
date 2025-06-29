# 2025_PangSiangLian

A full-stack Coin Exchange application designed to calculate the minimum number of coins required for specified monetary amount.

- **Backend**: Java 17 (Dropwizard framework)
- **Frontend**: React
- **Containerization**: Docker & Docker Compose

---

## 🚀 Features

✅ Calculate minimum coin denominations for input amount  
✅ REST API for coin calculation  
✅ Responsive frontend for user inputs  
✅ Dockerized deployment

---

## 🗂️ Project Structure
```plaintext
/
├── backend/
│ ├── src/
│ ├── pom.xml
│ ├── Dockerfile
│
├── frontend/
│ ├── src/
│ ├── package.json
│ ├── Dockerfile
│
└── docker-compose.yml
```

## ⚙️ How to Run Locally

### Clone the repo
Open your terminal and navigate to the directory where you want to clone the repo. Replace <your/file/path> with your actual local path:
```bash
git clone https://github.com/pangsianglian/2025_PangSiangLian.git
cd 2025_PangSiangLian
```

### Backend
Navigate to the backend project and build it:
```bash
cd backend/MinCoinExchange
mvn clean package
java -jar target/MinCoinExchange-1.0-SNAPSHOT.jar server src/main/resources/config.yml
```
By default, the backend runs on:
```bash
http://localhost:8080/coin-exchange
```
**Test the API locally:**
```bash
curl -X POST http://localhost:8080/coin-exchange -H "Content-Type: application/json" -d "{\"amount\": 6.75, \"denominations\": [10.0, 5.0, 1.0, 0.5, 0.2, 0.1, 0.05, 0.01]}"
```
**Sample Output:**
{"coins":[0.05,0.2,0.5,1.0,5.0]}

### Frontend
Stay in the root folder (<your-file-path>/2025_PangSiangLian):
```bash
cd frontend/min-coin-exchange-ui
npm install
npm start
```
The React app will launch automatically at:
```bash
http://localhost:3000/
```

## 🐳 Run with Docker
Alternatively, run the entire application using Docker Compose.
Make sure Docker Desktop is running. If not installed, download it here: [Docker Desktop](https://www.docker.com/products/docker-desktop/)

In the project root folder:
### Build Docker images
Run the following:
```bash
docker-compose up --build
```

### Start containers
```bash
docker-compose up
```
This will launch:

- Frontend → http://localhost:3000

- Backend API → http://localhost:8080/coin-exchange

### Stop container
To stop and remove the running containers:
```bash
docker-compose down
```


## 📝 License
This project is licensed under the MIT License.
