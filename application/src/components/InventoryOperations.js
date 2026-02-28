import React from 'react';

// Component for performing inventory operations
const InventoryOperations = () => {
  const handleInventoryOperation = async (operationType) => {
    // API call to perform inventory operation
    // Available to both Managers and Staff
  };

  return (
    <div>
      <h2>Inventory Operations</h2>
      <button onClick={() => handleInventoryOperation('add')}>Add Stock</button>
      <button onClick={() => handleInventoryOperation('remove')}>Remove Stock</button>
    </div>
  );
};

export default InventoryOperations;
