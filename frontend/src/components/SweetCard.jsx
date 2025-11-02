import React, { useState } from 'react'

const SweetCard = ({ sweet, isAdmin, onPurchase, onEdit, onDelete, onRestock }) => {
  const [restockQuantity, setRestockQuantity] = useState('')
  const [showRestock, setShowRestock] = useState(false)

  const handleRestockSubmit = (e) => {
    e.preventDefault()
    const quantity = parseInt(restockQuantity)
    if (quantity > 0) {
      onRestock(sweet.id, quantity)
      setRestockQuantity('')
      setShowRestock(false)
    }
  }

  return (
    <div className="sweet-card">
      <h3>{sweet.name}</h3>
      <div className="category">{sweet.category}</div>
      <div className="price">${sweet.price.toFixed(2)}</div>
      <div className={sweet.quantity === 0 ? 'out-of-stock' : 'quantity'}>
        {sweet.quantity === 0 ? 'Out of Stock' : `Stock: ${sweet.quantity}`}
      </div>
      
      <div style={{ display: 'flex', gap: '8px', flexWrap: 'wrap' }}>
        <button
          className="button"
          onClick={() => onPurchase(sweet.id)}
          disabled={sweet.quantity === 0}
          style={{ flex: 1 }}
        >
          {sweet.quantity === 0 ? 'Out of Stock' : 'Purchase'}
        </button>

        {isAdmin && (
          <>
            <button
              className="button button-secondary"
              onClick={() => onEdit(sweet)}
              style={{ flex: 1 }}
            >
              Edit
            </button>
            <button
              className="button button-danger"
              onClick={() => onDelete(sweet.id)}
              style={{ flex: 1 }}
            >
              Delete
            </button>
            {!showRestock ? (
              <button
                className="button"
                onClick={() => setShowRestock(true)}
                style={{ width: '100%' }}
              >
                Restock
              </button>
            ) : (
              <form onSubmit={handleRestockSubmit} style={{ width: '100%', display: 'flex', gap: '8px' }}>
                <input
                  type="number"
                  min="1"
                  value={restockQuantity}
                  onChange={(e) => setRestockQuantity(e.target.value)}
                  placeholder="Quantity"
                  style={{ flex: 1, padding: '8px' }}
                  required
                />
                <button type="submit" className="button">Add</button>
                <button
                  type="button"
                  className="button button-secondary"
                  onClick={() => {
                    setShowRestock(false)
                    setRestockQuantity('')
                  }}
                >
                  Cancel
                </button>
              </form>
            )}
          </>
        )}
      </div>
    </div>
  )
}

export default SweetCard

