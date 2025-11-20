```text
Synapse/
├── 📄 Dockerfile
├── 📁 src/main/java/com/fiap/gs_synapse/
│   ├── 📁 controller/
│   │   ├── CompetenciaViewController.java
│   │   ├── HomeViewController.java
│   │   ├── RecomendacaoProfissionalViewController.java
│   │   ├── RecomendacaoSaudeViewController.java
│   │   ├── RecomendacaoViewController.java
│   │   ├── RegistroBemEstarViewController.java
│   │   └── UsuarioViewController.java
│   ├── 📁 model/            # Entidades
│   │   ├── Competencia.java
│   │   ├── Recomendacao.java
│   │   ├── RecomendacaoProfissional.java
│   │   ├── RecomendacaoSaude.java
│   │   ├── RegistroBemEstar.java
│   │   └── Usuario.java
│   ├── 📁 repository/
│   │   ├── CompetenciaRepository.java
│   │   ├── RecomendacaoProfissionalRepository.java
│   │   ├── RecomendacaoRepository.java
│   │   ├── RecomendacaoSaudeRepository.java
│   │   ├── RegistroBemEstarRepository.java
│   │   └── UsuarioRepository.java
│   ├── 📁 service/
│   │   ├── CompetenciaService.java
│   │   ├── CustomUserDetailsService.java
│   │   ├── RecomendacaoProfissionalService.java
│   │   ├── RecomendacaoSaudeService.java
│   │   ├── RecomendacaoService.java
│   │   ├── RegistroBemEstarService.java
│   │   └── UsuarioService.java
│   ├── 📁 dto/
│   │   ├── CompetenciaDTO.java
│   │   ├── RecomendacaoDTO.java
│   │   ├── RecomendacaoProfissionalDTO.java
│   │   ├── RecomendacaoSaudeDTO.java
│   │   ├── RegistroBemEstarDTO.java
│   │   └── UsuarioDTO.java
│   ├── 📁 config/
│   │   ├── CacheConfig.java
│   │   ├── InternationalizationConfig.java
│   │   ├── JwtUtil.java
│   │   ├── JwtRequestFilter.java
│   │   ├── RabbitMQConfig.java
│   │   ├── SecurityConfig.java
│   │   └── WebConfig.java
│   └── 📁 exception/
│       ├── BusinessException.java
│       ├── CustomExceptionHandler.java
│       └── ResourceNotFoundException.java
├── 📁 messaging/
│   ├── EmailQueueListener.java
│   └── EmailQueueProducer.java
├── 📄 GsSynapseApplication.java
├── 📁 src/main/resources/
│   ├── 📁 db/migration/
│   │   ├── V2__novo_primeiro_script.sql
│   │   ├── V3__ajustar_restricao.sql
│   │   ├── V4__corrigir_admin_role.sql
│   │   ├── V5__creating_new_users.sql
│   │   └── V6__alter_table_usuario.sql
│   └── 📁 templates/
│       ├── 📁 static/
│       │   ├── 📁 css/
│       │   │   └── style.css
│       ├── 📁 competencias/
│       │   └── competencias.html
│       ├── 📁 home/
│       │   └── home.html
│       ├── 📁 login/
│       │   └── login.html
│       ├── 📁 recomendacoes/
│       │   └── recomendacoes.html
│       ├── 📁 recomendacoes-profissionais/
│       │   └── recomendacoes-profissionais.html
│       ├── 📁 recomendacoes-saude/
│       │   └── recomendacoes-saude.html
│       ├── 📁 registro-bem-estar/
│       │   └── registro-bem-estar.html
│       └── 📁 usuarios/
│           └── usuarios.html
└── 📄 pom.xml
```
