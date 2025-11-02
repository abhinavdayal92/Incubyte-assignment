const TOKEN_KEY = 'sweet_shop_token'
const USER_KEY = 'sweet_shop_user'

export const setAuthToken = (token) => {
  localStorage.setItem(TOKEN_KEY, token)
}

export const getAuthToken = () => {
  return localStorage.getItem(TOKEN_KEY)
}

export const removeAuthToken = () => {
  localStorage.removeItem(TOKEN_KEY)
  localStorage.removeItem(USER_KEY)
}

export const setUser = (user) => {
  localStorage.setItem(USER_KEY, JSON.stringify(user))
}

export const getUser = () => {
  const userStr = localStorage.getItem(USER_KEY)
  return userStr ? JSON.parse(userStr) : null
}

