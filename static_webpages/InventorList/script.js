function showInventor() {
    const inventor = document.getElementById("inventorList").value;

    const data = {
        gosling: {
            img: "images/gosling.jpg",
            info: "James Gosling is the creator of Java, developed at Sun Microsystems in 1995."
        },
        rossum: {
            img: "images/rossum.jpg",
            info: "Guido van Rossum created Python in 1991 and is known as the 'Benevolent Dictator For Life' of Python."
        },
        ritchie: {
            img: "images/ritchie.jpg",
            info: "Dennis Ritchie created the C programming language at Bell Labs in 1972."
        },
        stroustrup: {
            img: "images/stroustrup.jpg",
            info: "Bjarne Stroustrup developed C++ as an extension of C beginning in 1979."
        },
        eich: {
            img: "images/eich.jpg",
            info: "Brendan Eich created JavaScript in 1995 while working at Netscape."
        },
        lerdorf: {
            img: "images/lerdorf.jpg",
            info: "Rasmus Lerdorf created PHP in 1994 originally as CGI scripts."
        },
        matsumoto: {
            img: "images/matsumoto.jpg",
            info: "Yukihiro 'Matz' Matsumoto created Ruby in 1995 focusing on simplicity and productivity."
        },
        hejlsberg: {
            img: "images/hejlsberg.jpg",
            info: "Anders Hejlsberg developed C# for Microsoft in 2000."
        },
        hopper: {
            img: "images/hopper.jpg",
            info: "Grace Hopper created COBOL and pioneered compiler technology."
        },
        mccarthy: {
            img: "images/mccarthy.jpg",
            info: "John McCarthy created Lisp in 1958 and is considered one of the founders of AI."
        }
    };

    if (inventor === "") {
        document.getElementById("inventorImage").src = "";
        document.getElementById("infoBox").innerText = "Select an inventor to view details";
    } else {
        document.getElementById("inventorImage").src = data[inventor].img;
        document.getElementById("infoBox").innerText = data[inventor].info;
    }
}
