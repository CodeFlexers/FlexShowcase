import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './Home';
import Login from './Login/Login';
import Join from './Join/Join';
import { useEffect, useState } from 'react';
import { jwtDecode } from 'jwt-decode';
import MyPage from './MyPage';

function App() {

  const [user, setUser] = useState({});

  useEffect(() => {
    const token = localStorage.getItem('token');
    if(token){
      setUser(jwtDecode(token));
    }

  }, [])
  return (

    <div className="App">
      <header className="App-header">
        <div>CodeShowCase</div>
        <div>{user.accountNickname}, 하이</div>
      </header>

      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/login' element={<Login />} />
          <Route path='/join' element={<Join />} />
          <Route path="/mypage" element={<MyPage />} />
        </Routes>
      </BrowserRouter>
    
    </div>
  );
}

export default App;
