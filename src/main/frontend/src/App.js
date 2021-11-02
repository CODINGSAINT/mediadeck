
import AppRouter from './components/RouterComponent';
import Container from '@mui/material/Container';
import NavBar from './components/Navbar';
function App() {
  return (
    
    <div className="App">
     <NavBar></NavBar>
     <Container>
                <AppRouter/>
          </Container>
    </div>
  );
}

export default App;
