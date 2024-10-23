import './App.css';
import { BrowserRouter, Routes, Route, useNavigate } from 'react-router-dom';
import Home from './Home';
import Login from './Login/Login';
import Join from './Join/Join';
import MyPage from './MyPage';
import Header from './common/Header';
import CreateShowcase from './CreateShowcase';
import ManageShowcase from './ManageShowcase';


function App() {

  return (

    <div className="App">


      <BrowserRouter>
      <Header/>
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/login' element={<Login />} />
          <Route path='/join' element={<Join />} />
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/create-myShowcase" element={<CreateShowcase />} />
          <Route path="/manage-myShowcase" element={<ManageShowcase />} />
        </Routes>
        </BrowserRouter>

  
    </div>
  );
}

export default App;
