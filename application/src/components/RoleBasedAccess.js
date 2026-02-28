import React from 'react';

// Component for role-based access control
const RoleBasedAccess = ({ userRole }) => {
  return (
    <div>
      {userRole === 'Manager' && <ProductManagement />}
      {(userRole === 'Manager' || userRole === 'Staff') && <InventoryOperations />}
      {userRole === 'Viewer' && <p>You have read-only access.</p>}
    </div>
  );
};

export default RoleBasedAccess;
