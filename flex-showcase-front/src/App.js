import './App.css';
import { BrowserRouter, Routes, Route, useNavigate } from 'react-router-dom';
import Home from './Home';
import Login from './Login/Login';
import Join from './Join/Join';
import { useEffect, useState } from 'react';
import { jwtDecode } from 'jwt-decode';
import MyPage from './MyPage';
import CreateShowcase from './CreateShowcase';
import ManageShowcase from './ManageShowcase';

function App() {

  const [user, setUser] = useState({});
  const nav = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem('token');
    if(token){
      setUser(jwtDecode(token));
    }

  }, [])
  return (

    <div className="App">
      <header className="App-header">
        <div onClick={() => nav('/')}>CodeShowCase</div>
        <div className='App-user' onClick={() => nav('/mypage')}>
          <img src={user.profileImage} alt="프로필 사진" className="profile-image" 
            onError={(e) => e.target.src = './logo192.png'}
          /> <div>{user.accountNickname}, 하이</div>
        </div>
      </header>

        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/login' element={<Login />} />
          <Route path='/join' element={<Join />} />
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/create-myShowcase" element={<CreateShowcase />} />
          <Route path="/manage-myShowcase" element={<ManageShowcase />} />
        </Routes>

  
    </div>
  );
}

export default App;
