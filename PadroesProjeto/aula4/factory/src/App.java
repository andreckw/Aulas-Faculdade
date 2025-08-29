import AtividadeIndustria.ICalculaFrete;
import AtividadeIndustria.MiniPc;
import AtividadeIndustria.SoundBar;
import AtividadeIndustria.SuperServidor;
import FactoryMethod.Desenhista;
import FactoryMethod.FabricaCirculo;

public class App {
    public static void main(String[] args) throws Exception {
        

        Desenhista desenhista = new Desenhista(new FabricaCirculo());

        desenhista.desenhar();

        ICalculaFrete miniPc = new MiniPc();
        System.out.println(miniPc.frete());

        ICalculaFrete soundBar = new SoundBar();
        System.out.println(soundBar.frete());

        ICalculaFrete superServidor = new SuperServidor();
        System.out.println(superServidor.frete());
    }
}
