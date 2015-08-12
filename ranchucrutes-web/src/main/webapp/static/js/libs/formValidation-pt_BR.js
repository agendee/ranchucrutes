(function ($) {
    /**
     * Portuguese (Brazil) language package
     * Translated by @marcuscarvalho6. Improved by @dgmike
     */
    FormValidation.I18n = $.extend(true, FormValidation.I18n, {
        'pt_BR': {
            base64: {
                'default': 'Por favor insira um cÃ³digo base 64 vÃ¡lido'
            },
            between: {
                'default': 'Por favor insira um valor entre %s e %s',
                notInclusive: 'Por favor insira um valor estritamente entre %s e %s'
            },
            bic: {
                'default': 'Por favor insira um nÃºmero BIC vÃ¡lido'
            },
            callback: {
                'default': 'Por favor insira um valor vÃ¡lido'
            },
            choice: {
                'default': 'Por favor insira um valor vÃ¡lido',
                less: 'Por favor escolha %s opÃ§Ãµes no mÃ­nimo',
                more: 'Por favor escolha %s opÃ§Ãµes no mÃ¡ximo',
                between: 'Por favor escolha de %s a %s opÃ§Ãµes'
            },
            color: {
                'default': 'Por favor insira uma cor vÃ¡lida'
            },
            creditCard: {
                'default': 'Por favor insira um nÃºmero de cartÃ£o de crÃ©dito vÃ¡lido'
            },
            cusip: {
                'default': 'Por favor insira um nÃºmero CUSIP vÃ¡lido'
            },
            cvv: {
                'default': 'Por favor insira um nÃºmero CVV vÃ¡lido'
            },
            date: {
                'default': 'Por favor insira uma data vÃ¡lida',
                min: 'Por favor insira uma data posterior a %s',
                max: 'Por favor insira uma data anterior a %s',
                range: 'Por favor insira uma data entre %s e %s'
            },
            different: {
                'default': 'Por favor insira valores diferentes'
            },
            digits: {
                'default': 'Por favor insira somente dÃ­gitos'
            },
            ean: {
                'default': 'Por favor insira um nÃºmero EAN vÃ¡lido'
            },
            ein: {
                'default': 'Por favor insira um nÃºmero EIN vÃ¡lido'
            },
            emailAddress: {
                'default': 'Por favor insira um email vÃ¡lido'
            },
            file: {
                'default': 'Por favor escolha um arquivo vÃ¡lido'
            },
            greaterThan: {
                'default': 'Por favor insira um valor maior ou igual a %s',
                notInclusive: 'Por favor insira um valor maior do que %s'
            },
            grid: {
                'default': 'Por favor insira uma GRID vÃ¡lida'
            },
            hex: {
                'default': 'Por favor insira um hexadecimal vÃ¡lido'
            },
            iban: {
                'default': 'Por favor insira um nÃºmero IBAN vÃ¡lido',
                country: 'Por favor insira um nÃºmero IBAN vÃ¡lido em %s',
                countries: {
                    AD: 'Andorra',
                    AE: 'Emirados Ãrabes',
                    AL: 'AlbÃ¢nia',
                    AO: 'Angola',
                    AT: 'Ãustria',
                    AZ: 'AzerbaijÃ£o',
                    BA: 'BÃ³snia-Herzegovina',
                    BE: 'BÃ©lgica',
                    BF: 'Burkina Faso',
                    BG: 'BulgÃ¡ria',
                    BH: 'Bahrain',
                    BI: 'Burundi',
                    BJ: 'Benin',
                    BR: 'Brasil',
                    CH: 'SuÃ­Ã§a',
                    IC: 'Costa do Marfim',
                    CM: 'CamarÃµes',
                    CR: 'Costa Rica',
                    CV: 'Cabo Verde',
                    CY: 'Chipre',
                    CZ: 'RepÃºblica Checa',
                    DE: 'Alemanha',
                    DK: 'Dinamarca',
                    DO: 'RepÃºblica Dominicana',
                    DZ: 'ArgÃ©lia',
                    EE: 'EstÃ³nia',
                    ES: 'Espanha',
                    FI: 'FinlÃ¢ndia',
                    FO: 'Ilhas FaroÃ©',
                    FR: 'FranÃ§a',
                    GB: 'Reino Unido',
                    GE: 'Georgia',
                    GI: 'Gibraltar',
                    GL: 'GroenlÃ¢ndia',
                    GR: 'GrÃ©cia',
                    GT: 'Guatemala',
                    HR: 'CroÃ¡cia',
                    HU: 'Hungria',
                    IE: 'Ireland',
                    IL: 'Israel',
                    IR: 'IrÃ£o',
                    IS: 'IslÃ¢ndia',
                    TI: 'ItÃ¡lia',
                    JO: 'Jordan',
                    KW: 'Kuwait',
                    KZ: 'CazaquistÃ£o',
                    LB: 'LÃ­bano',
                    LI: 'Liechtenstein',
                    LT: 'LituÃ¢nia',
                    LU: 'Luxemburgo',
                    LV: 'LetÃ³nia',
                    MC: 'MÃ´naco',
                    MD: 'MoldÃ¡via',
                    ME: 'Montenegro',
                    MG: 'Madagascar',
                    MK: 'MacedÃ³nia',
                    ML: 'Mali',
                    MR: 'MauritÃ¢nia',
                    MT: 'Malta',
                    MU: 'MaurÃ­cio',
                    MZ: 'MoÃ§ambique',
                    NL: 'PaÃ­ses Baixos',
                    NO: 'Noruega',
                    PK: 'PaquistÃ£o',
                    PL: 'PolÃ´nia',
                    PS: 'Palestino',
                    PT: 'Portugal',
                    QA: 'Qatar',
                    RO: 'RomÃ©nia',
                    RS: 'SÃ©rvia',
                    SA: 'ArÃ¡bia Saudita',
                    SE: 'SuÃ©cia',
                    SI: 'EslovÃ©nia',
                    SK: 'EslovÃ¡quia',
                    SM: 'San Marino',
                    SN: 'Senegal',
                    TN: 'TunÃ­sia',
                    TR: 'Turquia',
                    VG: 'Ilhas Virgens BritÃ¢nicas'
                }
            },
            id: {
                'default': 'Por favor insira um cÃ³digo de identificaÃ§Ã£o vÃ¡lido',
                country: 'Por favor insira um nÃºmero de indentificaÃ§Ã£o vÃ¡lido em %s',
                countries: {
                    BA: 'BÃ³snia e Herzegovina',
                    BG: 'BulgÃ¡ria',
                    BR: 'Brasil',
                    CH: 'SuÃ­Ã§a',
                    CL: 'Chile',
                    CN: 'China',
                    CZ: 'RepÃºblica Checa',
                    DK: 'Dinamarca',
                    EE: 'EstÃ´nia',
                    ES: 'Espanha',
                    FI: 'FinlÃ¢ndia',
                    HR: 'CroÃ¡cia',
                    IE: 'Irlanda',
                    IS: 'IslÃ¢ndia',
                    LT: 'LituÃ¢nia',
                    LV: 'LetÃ³nia',
                    ME: 'Montenegro',
                    MK: 'MacedÃ³nia',
                    NL: 'Holanda',
                    PL: 'PolÃ´nia',
                    RO: 'RomÃ©nia',
                    RS: 'SÃ©rvia',
                    SE: 'SuÃ©cia',
                    SI: 'EslovÃªnia',
                    SK: 'EslovÃ¡quia',
                    SM: 'San Marino',
                    TH: 'TailÃ¢ndia',
                    ZA: 'Ãfrica do Sul'
                }
            },
            identical: {
                'default': 'Por favor, insira o mesmo valor'
            },
            imei: {
                'default': 'Por favor insira um IMEI vÃ¡lido'
            },
            imo: {
                'default': 'Por favor insira um IMO vÃ¡lido'
            },
            integer: {
                'default': 'Por favor insira um nÃºmero inteiro vÃ¡lido'
            },
            ip: {
                'default': 'Por favor insira um IP vÃ¡lido',
                ipv4: 'Por favor insira um endereÃ§o de IPv4 vÃ¡lido',
                ipv6: 'Por favor insira um endereÃ§o de IPv6 vÃ¡lido'
            },
            isbn: {
                'default': 'Por favor insira um ISBN vÃ¡lido'
            },
            isin: {
                'default': 'Por favor insira um ISIN vÃ¡lido'
            },
            ismn: {
                'default': 'Por favor insira um ISMN vÃ¡lido'
            },
            issn: {
                'default': 'Por favor insira um ISSN vÃ¡lido'
            },
            lessThan: {
                'default': 'Por favor insira um valor menor ou igual a %s',
                notInclusive: 'Por favor insira um valor menor do que %s'
            },
            mac: {
                'default': 'Por favor insira um endereÃ§o MAC vÃ¡lido'
            },
            meid: {
                'default': 'Por favor insira um MEID vÃ¡lido'
            },
            notEmpty: {
                'default': 'Por favor insira um valor'
            },
            numeric: {
                'default': 'Por favor insira um nÃºmero real vÃ¡lido'
            },
            phone: {
                'default': 'Por favor insira um nÃºmero de telefone vÃ¡lido',
                country: 'Por favor insira um nÃºmero de telefone vÃ¡lido em %s',
                countries: {
                    AE: 'Emirados Ãrabes',
                    BG: 'BulgÃ¡ria',
                    BR: 'Brasil',
                    CN: 'China',
                    CZ: 'RepÃºblica Checa',
                    DE: 'Alemanha',
                    DK: 'Dinamarca',
                    ES: 'Espanha',
                    FR: 'FranÃ§a',
                    GB: 'Reino Unido',
                    IN: 'Ãndia',
                    MA: 'Marrocos',
                    NL: 'PaÃ­ses Baixos',
                    PK: 'PaquistÃ£o',
                    RO: 'RomÃ©nia',
                    RU: 'RÃºssia',
                    SK: 'EslovÃ¡quia',
                    TH: 'TailÃ¢ndia',
                    US: 'EUA',
                    VE: 'Venezuela'
                }
            },
            regexp: {
                'default': 'Por favor insira um valor correspondente ao padrÃ£o'
            },
            remote: {
                'default': 'Por favor insira um valor vÃ¡lido'
            },
            rtn: {
                'default': 'Por favor insira um nÃºmero vÃ¡lido RTN'
            },
            sedol: {
                'default': 'Por favor insira um nÃºmero vÃ¡lido SEDOL'
            },
            siren: {
                'default': 'Por favor insira um nÃºmero vÃ¡lido SIREN'
            },
            siret: {
                'default': 'Por favor insira um nÃºmero vÃ¡lido SIRET'
            },
            step: {
                'default': 'Por favor insira um passo vÃ¡lido %s'
            },
            stringCase: {
                'default': 'Por favor, digite apenas caracteres minÃºsculos',
                upper: 'Por favor, digite apenas caracteres maiÃºsculos'
            },
            stringLength: {
                'default': 'Por favor insira um valor com comprimento vÃ¡lido',
                less: 'Por favor insira menos de %s caracteres',
                more: 'Por favor insira mais de %s caracteres',
                between: 'Por favor insira um valor entre %s e %s caracteres'
            },
            uri: {
                'default': 'Por favor insira um URI vÃ¡lido'
            },
            uuid: {
                'default': 'Por favor insira um nÃºmero vÃ¡lido UUID',
                version: 'Por favor insira uma versÃ£o %s  UUID vÃ¡lida'
            },
            vat: {
                'default': 'Por favor insira um VAT vÃ¡lido',
                country: 'Por favor insira um nÃºmero VAT vÃ¡lido em %s',
                countries: {
                    AT: 'Ãustria',
                    BE: 'BÃ©lgica',
                    BG: 'BulgÃ¡ria',
                    BR: 'Brasil',
                    CH: 'SuÃ­Ã§a',
                    CY: 'Chipre',
                    CZ: 'RepÃºblica Checa',
                    DE: 'Alemanha',
                    DK: 'Dinamarca',
                    EE: 'EstÃ´nia',
                    ES: 'Espanha',
                    FI: 'FinlÃ¢ndia',
                    FR: 'FranÃ§a',
                    GB: 'Reino Unido',
                    GR: 'GrÃ©cia',
                    EL: 'GrÃ©cia',
                    HU: 'Hungria',
                    HR: 'CroÃ¡cia',
                    IE: 'Irlanda',
                    IS: 'IslÃ¢ndia',
                    IT: 'ItÃ¡lia',
                    LT: 'LituÃ¢nia',
                    LU: 'Luxemburgo',
                    LV: 'LetÃ³nia',
                    MT: 'Malta',
                    NL: 'Holanda',
                    NO: 'Norway',
                    PL: 'PolÃ´nia',
                    PT: 'Portugal',
                    RO: 'RomÃ©nia',
                    RU: 'RÃºssia',
                    RS: 'SÃ©rvia',
                    SE: 'SuÃ©cia',
                    SI: 'EslovÃªnia',
                    SK: 'EslovÃ¡quia',
                    VE: 'Venezuela',
                    ZA: 'Ãfrica do Sul'
                }
            },
            vin: {
                'default': 'Por favor insira um VIN vÃ¡lido'
            },
            zipCode: {
                'default': 'Por favor insira um cÃ³digo postal vÃ¡lido',
                country: 'Por favor insira um cÃ³digo postal vÃ¡lido em %s',
                countries: {
                    AT: 'Ãustria',
                    BG: 'BulgÃ¡ria',
                    BR: 'Brasil',
                    CA: 'CanadÃ¡',
                    CH: 'SuÃ­Ã§a',
                    CZ: 'RepÃºblica Checa',
                    DE: 'Alemanha',
                    DK: 'Dinamarca',
                    ES: 'Espanha',
                    FR: 'FranÃ§a',
                    GB: 'Reino Unido',
                    IE: 'Irlanda',
                    IN: 'Ãndia',
                    IT: 'ItÃ¡lia',
                    MA: 'Marrocos',
                    NL: 'Holanda',
                    PL: 'PolÃ´nia',
                    PT: 'Portugal',
                    RO: 'RomÃ©nia',
                    RU: 'RÃºssia',
                    SE: 'SuÃ©cia',
                    SG: 'Cingapura',
                    SK: 'EslovÃ¡quia',
                    US: 'EUA'
                }
            }
        }
    });
}(jQuery));
