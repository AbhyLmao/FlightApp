import React from 'react';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const Success = () => {
  const navigate = useNavigate();
  
  function handleClick() {
    navigate('/menu');
  }

  return (
    <div>
      <h2>Action successful</h2>
      <button onClick={()=>handleClick()}>Menu</button>
    </div>
  );
};

export default Success;