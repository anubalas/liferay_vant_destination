import React, { useState } from 'react';

// Component for managing products
const ProductManagement = () => {
  const [product, setProduct] = useState({ name: '', price: '', description: '' });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setProduct({ ...product, [name]: value });
  };

  const handleAddProduct = async () => {
    // API call to add product
    // Ensure only Managers can access this feature
  };

  return (
    <div>
      <h2>Add Product</h2>
      <input type="text" name="name" placeholder="Product Name" onChange={handleInputChange} />
      <input type="number" name="price" placeholder="Product Price" onChange={handleInputChange} />
      <textarea name="description" placeholder="Product Description" onChange={handleInputChange}></textarea>
      <button onClick={handleAddProduct}>Add Product</button>
    </div>
  );
};

export default ProductManagement;
