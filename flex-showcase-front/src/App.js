import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './Home';

function App() {
  return (

    <div className="App">
      <header className="App-header">
        <div>CodeShowCase</div>
      </header>

      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Home />} />
        </Routes>
      </BrowserRouter>
    
    </div>
  );
}

export default App;
