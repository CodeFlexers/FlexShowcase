import './App.css';
import { BrowserRouter, Routes, Route, useNavigate } from 'react-router-dom';
import Header from './common/Header';
import Home from './page/home/Home';
import Login from './page/login/Login';
import Join from './page/join/Join';
import Mypage from './page/mypage/MyPage';
import CreateShowcase from './page/showcase/createShowcase/CreateShowcase';
import ManageShowcase from './page/showcase/manageShowcase/ManageShowcase';



function App() {

  return (

    <div className="App">
      <BrowserRouter>
      <Header/>

        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/login' element={<Login />} />
          <Route path='/join' element={<Join />} />
          <Route path="/mypage" element={<Mypage />} />
          <Route path="/create-myShowcase" element={<CreateShowcase />} />
          <Route path="/manage-myShowcase" element={<ManageShowcase />} />
        </Routes>
      </BrowserRouter>

    </div>
  );
}

export default App;
