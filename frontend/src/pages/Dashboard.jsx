import React, { useState, useEffect } from 'react'
import api from '../utils/api'
import { getUser } from '../utils/auth'
import SweetCard from '../components/SweetCard'
import SweetModal from '../components/SweetModal'
import SearchBar from '../components/SearchBar'

const Dashboard = ({ onLogout }) => {
  const [sweets, setSweets] = useState([])
  const [filteredSweets, setFilteredSweets] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [success, setSuccess] = useState('')
  const [showModal, setShowModal] = useState(false)
  const [editingSweet, setEditingSweet] = useState(null)
  const [searchFilters, setSearchFilters] = useState({
    name: '',
    category: '',
    minPrice: '',
    maxPrice: '',
  })
  const user = getUser()
  const isAdmin = user?.isAdmin || false

  useEffect(() => {
    fetchSweets()
  }, [])

  useEffect(() => {
    filterSweets()
  }, [sweets, searchFilters])

  const fetchSweets = async () => {
    try {
      setLoading(true)
      const response = await api.get('/sweets')
      setSweets(response.data)
      setFilteredSweets(response.data)
    } catch (err) {
      setError('Failed to fetch sweets. Please try again.')
    } finally {
      setLoading(false)
    }
  }

  const filterSweets = async () => {
    try {
      const params = new URLSearchParams()
      if (searchFilters.name) params.append('name', searchFilters.name)
      if (searchFilters.category) params.append('category', searchFilters.category)
      if (searchFilters.minPrice) params.append('minPrice', searchFilters.minPrice)
      if (searchFilters.maxPrice) params.append('maxPrice', searchFilters.maxPrice)

      const response = await params.toString() 
        ? await api.get(`/sweets/search?${params.toString()}`)
        : await api.get('/sweets')
      
      setFilteredSweets(response.data)
    } catch (err) {
      setError('Failed to search sweets.')
    }
  }

  const handlePurchase = async (id) => {
    try {
      await api.post(`/sweets/${id}/purchase`)
      setSuccess('Sweet purchased successfully!')
      fetchSweets()
      setTimeout(() => setSuccess(''), 3000)
    } catch (err) {
      setError(err.response?.data?.error || 'Failed to purchase sweet.')
      setTimeout(() => setError(''), 3000)
    }
  }

  const handleCreate = () => {
    setEditingSweet(null)
    setShowModal(true)
  }

  const handleEdit = (sweet) => {
    setEditingSweet(sweet)
    setShowModal(true)
  }

  const handleDelete = async (id) => {
    if (!window.confirm('Are you sure you want to delete this sweet?')) return

    try {
      await api.delete(`/sweets/${id}`)
      setSuccess('Sweet deleted successfully!')
      fetchSweets()
      setTimeout(() => setSuccess(''), 3000)
    } catch (err) {
      setError(err.response?.data?.error || 'Failed to delete sweet.')
      setTimeout(() => setError(''), 3000)
    }
  }

  const handleRestock = async (id, quantity) => {
    try {
      await api.post(`/sweets/${id}/restock`, { quantity })
      setSuccess('Sweet restocked successfully!')
      fetchSweets()
      setTimeout(() => setSuccess(''), 3000)
    } catch (err) {
      setError(err.response?.data?.error || 'Failed to restock sweet.')
      setTimeout(() => setError(''), 3000)
    }
  }

  const handleModalClose = () => {
    setShowModal(false)
    setEditingSweet(null)
    fetchSweets()
  }

  return (
    <div className="container">
      <div className="header">
        <h1>🍬 Sweet Shop Management System</h1>
        <div className="header-actions">
          <span className="user-info">Welcome, {user?.username}!</span>
          {isAdmin && <span style={{ color: '#667eea', fontWeight: 'bold' }}>👑 Admin</span>}
          <button className="button button-secondary" onClick={onLogout}>
            Logout
          </button>
        </div>
      </div>

      {error && <div className="error">{error}</div>}
      {success && <div className="success">{success}</div>}

      {isAdmin && (
        <div style={{ marginBottom: '24px' }}>
          <button className="button" onClick={handleCreate}>
            ➕ Add New Sweet
          </button>
        </div>
      )}

      <SearchBar 
        filters={searchFilters} 
        onChange={setSearchFilters}
      />

      {loading ? (
        <div style={{ textAlign: 'center', padding: '40px', color: '#6c757d' }}>
          Loading sweets...
        </div>
      ) : filteredSweets.length === 0 ? (
        <div className="card" style={{ textAlign: 'center', padding: '40px', color: '#6c757d' }}>
          No sweets found. {isAdmin && 'Add some sweets to get started!'}
        </div>
      ) : (
        <div className="grid">
          {filteredSweets.map((sweet) => (
            <SweetCard
              key={sweet.id}
              sweet={sweet}
              isAdmin={isAdmin}
              onPurchase={handlePurchase}
              onEdit={handleEdit}
              onDelete={handleDelete}
              onRestock={handleRestock}
            />
          ))}
        </div>
      )}

      {showModal && (
        <SweetModal
          sweet={editingSweet}
          onClose={handleModalClose}
          onSuccess={() => {
            setSuccess('Sweet saved successfully!')
            setTimeout(() => setSuccess(''), 3000)
          }}
          onError={(err) => {
            setError(err)
            setTimeout(() => setError(''), 3000)
          }}
        />
      )}
    </div>
  )
}

export default Dashboard

