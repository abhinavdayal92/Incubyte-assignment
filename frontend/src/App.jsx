import React, { useState, useEffect } from 'react'
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom'
import Login from './pages/Login'
import Register from './pages/Register'
import Dashboard from './pages/Dashboard'
import { getAuthToken, removeAuthToken, setAuthToken } from './utils/auth'

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(!!getAuthToken())

  const handleLogin = (token) => {
    setAuthToken(token)
    setIsAuthenticated(true)
  }

  const handleLogout = () => {
    removeAuthToken()
    setIsAuthenticated(false)
  }

  return (
    <Router>
      <Routes>
        <Route 
          path="/login" 
          element={
            isAuthenticated ? 
            <Navigate to="/" replace /> : 
            <Login onLogin={handleLogin} />
          } 
        />
        <Route 
          path="/register" 
          element={
            isAuthenticated ? 
            <Navigate to="/" replace /> : 
            <Register onLogin={handleLogin} />
          } 
        />
        <Route 
          path="/" 
          element={
            isAuthenticated ? 
            <Dashboard onLogout={handleLogout} /> : 
            <Navigate to="/login" replace />
          } 
        />
      </Routes>
    </Router>
  )
}

export default App

