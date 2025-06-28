# 2025_PangSiangLian

A full-stack Coin Exchange application designed to calculate the minimum number of coins required for specified monetary amount.

- **Backend**: Java (Dropwizard framework)
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

```bash
git clone https://github.com/pangsianglian/2025_PangSiangLian.git
cd 2025_PangSiangLian
```

### Backend
```bash
cd MinCoinExchange
mvn clean package
java -jar target/MinCoinExchange-1.0-SNAPSHOT.jar server src/main/resources/config.yml
```
By default, the backend runs on:
```bash
http://localhost:8080/coin-exchange
```

### Frontend
```bash
cd min-coin-exchange-ui
npm install
npm start
```

## 🐳 Run with Docker
Alternatively, run the entire application using Docker Compose:
### Build Docker images
```bash
docker-compose build
```

### Start containers
```bash
docker-compose up
```
This will launch:

- Frontend → http://localhost

- Backend API → http://localhost:8080/coin-exchange


## 📝 License
This project is licensed under the MIT License.
