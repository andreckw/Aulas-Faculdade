using System.Text;

namespace Conversor;

class Base64
{
    public string Criptografar(string texto)
    {
        var elementos = this.CarregarElementos();
        Encoding ascii = Encoding.ASCII;

        Byte[] bytes = ascii.GetBytes(texto);

        var textoBinario = new List<String>();
        foreach(int b in bytes) {
            var aux = Convert.ToString(b, 2);
            textoBinario.Add("0" + aux);
        }

        var textoBinarioFragmentado = "";
        foreach(string t in textoBinario) {
            textoBinarioFragmentado += t;
        }

        int qtdPadding = 0;
        while (textoBinarioFragmentado.Length % 6 != 0) {
            textoBinarioFragmentado += "00000000";
            qtdPadding++;
        }

        var binarios = new List<String>();
        for (int i = 0; i < textoBinarioFragmentado.Length; i+=6) {
            binarios.Add("" + textoBinarioFragmentado[i] + textoBinarioFragmentado[i+1]
                + textoBinarioFragmentado[i+2] + textoBinarioFragmentado[i+3]
                + textoBinarioFragmentado[i+4] + textoBinarioFragmentado[i+5]
                );
        }

        var newTexto = "";
        foreach (string b in binarios) {
            newTexto += elementos[Convert.ToInt32(b, 2)];
        }

        newTexto = newTexto.Remove(newTexto.Length - qtdPadding);

        while (qtdPadding > 0 ) {
            newTexto += "=";
            qtdPadding--;
        }

        return newTexto;
    }

    public string Descriptografar(string textoBase64)
    {
        int qtdPadding = textoBase64.Split("=").Length - 1;
        
        textoBase64 = textoBase64.Replace("=", "");

        var elementos = CarregarElementos().ToDictionary(x => x.Value, x => x.Key);

        var textoBinario = "";
        for (int i = 0; i < textoBase64.Length; i++) {
            var elem = elementos[textoBase64[i]];
            
            string aux = Convert.ToString(elem, 2);

            while (aux.Length < 6) {
                aux = "0" + aux;
            }

            textoBinario += aux;
        }

        int j = qtdPadding; 
        while (j > 0 ) {
            textoBinario += "000000";
            j--;
        }

        Byte[] bytes = new Byte[textoBinario.Length / 8];
        Encoding ascii = Encoding.ASCII;
        for (int i = 0; i < textoBinario.Length; i+=8) {
            var aux = Convert.ToInt32(textoBinario.Substring(i, 8), 2);

            bytes[i/8] = Convert.ToByte(aux);
        }

        string textoDescriptografado = ascii.GetString(bytes);

        return textoDescriptografado.Substring(0, textoDescriptografado.Length - qtdPadding);
    }

    private Dictionary<int, char> CarregarElementos()
    {
        return new Dictionary<int, char> {
            {0,'A'}, {1,'B'}, {2,'C'}, {3,'D'}, {4,'E'}, {5,'F'}, {6,'G'}, {7,'H'}, {8,'I'},
            {9,'J'}, {10,'K'}, {11,'L'}, {12,'M'}, {13,'N'}, {14,'O'}, {15,'P'}, {16,'Q'}, 
            {17,'R'}, {18,'S'}, {19,'T'}, {20,'U'}, {21,'V'}, {22,'W'}, {23,'X'}, {24,'Y'},
            {25,'Z'}, {26,'a'}, {27,'b'}, {28,'c'}, {29,'d'}, {30,'e'}, {31,'f'}, {32,'g'},
            {33,'h'}, {34,'i'}, {35,'j'}, {36,'k'}, {37,'l'}, {38,'m'}, {39,'n'}, {40,'o'}, 
            {41,'p'}, {42,'q'}, {43,'r'},{44,'s'}, {45,'t'}, {46,'u'}, {47,'v'}, {48,'w'}, 
            {49,'x'}, {50,'y'}, {51,'z'}, {52,'0'}, {53,'1'}, {54,'2'}, {55,'3'}, {56,'4'},
            {57,'5'}, {58,'6'}, {59,'7'}, {60,'8'}, {61,'9'}, {62,'+'}, {63,'/'},
        };
    }
}