import React, { useState } from "react";
import axios from "axios";

function App() {
  const allowedDenominations = [
    0.01, 0.05, 0.1, 0.2, 0.5, 1, 2, 5, 10, 50, 100, 1000,
  ];

  const [amount, setAmount] = useState("");
  const [selectedDenoms, setSelectedDenoms] = useState([]);
  const [response, setResponse] = useState(null);
  const [error, setError] = useState(null);

  const handleDenomChange = (e) => {
    const value = parseFloat(e.target.value);
    if (e.target.checked) {
      setSelectedDenoms([...selectedDenoms, value]);
    } else {
      setSelectedDenoms(selectedDenoms.filter((d) => d !== value));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.post("http://13.229.72.207:8080/coin-exchange", {
        amount: parseFloat(amount),
        denominations: selectedDenoms,
      });
      setResponse(res.data.coins);
      setError(null);
    } catch (err) {
      console.error(err);
      setError(err.response?.data?.message || "Error occurred");
      setResponse(null);
    }
  };

  const targetAmount = parseFloat(amount);

  return (
    <div className="App" style={{ padding: "20px" }}>
      <h1>Minimum Denominations Calculator</h1>

      <form onSubmit={handleSubmit}>
        <div>
          <label>Target Amount:</label>
          <input
            type="number"
            step="0.01"
            value={amount}
            onChange={(e) => {
              setAmount(e.target.value);
              setSelectedDenoms([]);
            }}
            required
          />
        </div>

        <div>
          <label>Select the Denominations:</label>
          <div className="checkbox-group">
            {allowedDenominations.map((denom) => {
              const disabled = amount !== "" && targetAmount < denom;
              return (
                <label
                  key={denom}
                  style={{
                    marginRight: "10px",
                    opacity: disabled ? 0.5 : 1,
                    cursor: disabled ? "not-allowed" : "pointer",
                  }}
                >
                  <input
                    type="checkbox"
                    value={denom}
                    checked={selectedDenoms.includes(denom)}
                    onChange={handleDenomChange}
                    disabled={disabled}
                  />
                  {denom < 1 ? denom.toFixed(2) : denom}
                </label>
              );
            })}
          </div>
        </div>

        <button type="submit" disabled={selectedDenoms.length === 0}>
          Calculate
        </button>
      </form>

      {response && (
        <div>
          <h2>Minimum number of Denominations:</h2>
            <p>
              {response
                .map((coin) => (coin < 1 ? coin.toFixed(2) : coin))
                .join(", ")}
            </p>
                <ul>
                  {Object.entries(
                    response.reduce((acc, coin) => {
                      const key = coin < 1 ? coin.toFixed(2) : coin;
                      acc[key] = (acc[key] || 0) + 1;
                      return acc;
                    }, {})
                  )
                  .sort((a, b) => parseFloat(b[0]) - parseFloat(a[0]))
                  .map(([coin, count]) => (
                    <li key={coin}>
                      {coin} × {count}
                    </li>
                  ))}
                </ul>
        </div>
      )}

      {error && <p style={{ color: "red" }}>{error}</p>}
    </div>
  );
}

export default App;
