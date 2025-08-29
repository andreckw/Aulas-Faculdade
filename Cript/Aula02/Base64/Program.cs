using System;
using System.Text;
using Conversor;

namespace Program;

class Program
{
    static void Main()
    {
        string texto = "Manow";
        
        Base64 base64 = new Base64();
        string newTexto = base64.Criptografar(texto);

        Console.WriteLine("Texto original: " + texto + " - Texto Base64: " + newTexto);

        string descTexto = base64.Descriptografar(newTexto);

        Console.WriteLine("Texto base64: " + newTexto + " - Texto Original: " + descTexto);
    }

    static List<string> CarregarElemento64()
    {
        return new List<string> {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2",
            "3", "4", "5", "6", "7", "8", "9", "+", "/",
        };
    }
}

