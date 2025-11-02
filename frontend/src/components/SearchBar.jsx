import React from 'react'

const SearchBar = ({ filters, onChange }) => {
  const handleChange = (field, value) => {
    onChange({
      ...filters,
      [field]: value,
    })
  }

  return (
    <div className="card">
      <h3 style={{ marginBottom: '16px', color: '#333' }}>🔍 Search & Filter</h3>
      <div className="search-bar">
        <input
          type="text"
          className="input"
          placeholder="Search by name..."
          value={filters.name}
          onChange={(e) => handleChange('name', e.target.value)}
        />
        <input
          type="text"
          className="input"
          placeholder="Filter by category..."
          value={filters.category}
          onChange={(e) => handleChange('category', e.target.value)}
        />
        <input
          type="number"
          className="input"
          placeholder="Min price..."
          value={filters.minPrice}
          onChange={(e) => handleChange('minPrice', e.target.value)}
          min="0"
          step="0.01"
        />
        <input
          type="number"
          className="input"
          placeholder="Max price..."
          value={filters.maxPrice}
          onChange={(e) => handleChange('maxPrice', e.target.value)}
          min="0"
          step="0.01"
        />
      </div>
    </div>
  )
}

export default SearchBar

