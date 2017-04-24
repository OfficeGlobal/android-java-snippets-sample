﻿# <a name="snippets-sample-for-android-using-the-microsoft-graph-sdk"></a>Ejemplo de fragmentos de código para Android con SDK de Microsoft Graph

[![Estado de compilación](https://travis-ci.org/microsoftgraph/android-java-snippets-sample.svg?branch=master)](https://travis-ci.org/microsoftgraph/android-java-snippets-sample)

**Tabla de contenido**

* [Requisitos del dispositivo](#device-requirement)
* [Requisitos previos](#prerequisites)
* [Registro de aplicación de cliente de Azure](#azure-client-application-registration)
* [Configurar el proyecto](#configure-the-project)
* [Ejecutar el proyecto](#run-the-project)
* [Repercusión de la muestra en los datos del espacio empresarial](#how-the-sample-affects-your-tenant-data)
* [Entender el código](#understand-the-code)
* [Preguntas y comentarios](#questions-and-comments)
* [Recursos adicionales](#additional-resources)

¿Desea crear aplicaciones interesantes que ayudan a los usuarios a trabajar con sus datos de Office 365? Explore, aprenda y comprenda el SDK de Microsoft Graph mediante el uso de este ejemplo de fragmentos de código. Este ejemplo muestra cómo acceder a varios recursos, incluido Microsoft Azure Active Directory (AD) y Office 365, realizando llamadas al SDK de Microsoft Graph en una aplicación de Android.

Puede explorar las siguientes operaciones en Microsoft Graph:

**Yo**

* [Obtiene información sobre el usuario con sesión iniciada.](app/src/main/java/com/microsoft/graph/snippets/snippet/MeSnippets.java#L44)
* [Obtiene las responsabilidades del usuario.](app/src/main/java/com/microsoft/graph/snippets/snippet/MeSnippets.java#L67)
* [Obtiene el administrador del usuario.](app/src/main/java/com/microsoft/graph/snippets/snippet/MeSnippets.java#L94)
* [Obtiene los subordinados directos del usuario.](app/src/main/java/com/microsoft/graph/snippets/snippet/MeSnippets.java#L117)
* [Obtiene la pertenencia a grupos del usuario.](app/src/main/java/com/microsoft/graph/snippets/snippet/MeSnippets.java#L140)
* [Obtiene la foto del usuario.](app/src/main/java/com/microsoft/graph/snippets/snippet/MeSnippets.java#L164)

**Usuarios**

* [Obtiene usuarios del directorio del espacio empresarial.](app/src/main/java/com/microsoft/graph/snippets/snippet/UsersSnippets.java#L49)
* [Obtiene los usuarios filtrados mediante los criterios del directorio del espacio empresarial.](app/src/main/java/com/microsoft/graph/snippets/snippet/UsersSnippets.java#L80)
* [Crea un usuario.](app/src/main/java/com/microsoft/graph/snippets/snippet/UsersSnippets.java#L113)

**Eventos**

* [Obtiene los eventos del usuario con sesión iniciada.](app/src/main/java/com/microsoft/graph/snippets/snippet/EventsSnippets.java#L51)
* [Crea un evento para el usuario.](app/src/main/java/com/microsoft/graph/snippets/snippet/EventsSnippets.java#L83)
* [Actualiza el evento de un usuario.](app/src/main/java/com/microsoft/graph/snippets/snippet/EventsSnippets.java#L118)
* [Elimina el evento de un usuario.](app/src/main/java/com/microsoft/graph/snippets/snippet/EventsSnippets.java#L170)

**Mensajes**

* [Obtiene mensajes del usuario.](app/src/main/java/com/microsoft/graph/snippets/snippet/MessageSnippets.java#L51)
* [Envía un mensaje desde el buzón del usuario.](app/src/main/java/com/microsoft/graph/snippets/snippet/MessageSnippets.java#L82)

**Grupos**

* [Obtiene todos los grupos del directorio del espacio empresarial.](app/src/main/java/com/microsoft/graph/snippets/snippet/GroupsSnippets.java#L184)
* [Crea un grupo.](app/src/main/java/com/microsoft/graph/snippets/snippet/GroupsSnippets.java#L191)
* [Obtiene información sobre un grupo específico del espacio empresarial.](app/src/main/java/com/microsoft/graph/snippets/snippet/GroupsSnippets.java#L45)
* [Elimina un grupo.](app/src/main/java/com/microsoft/graph/snippets/snippet/GroupsSnippets.java#L248)
* [Obtiene los miembros de un grupo.](app/src/main/java/com/microsoft/graph/snippets/snippet/GroupsSnippets.java#L90)
* [Obtiene los propietarios de un grupo.](app/src/main/java/com/microsoft/graph/snippets/snippet/GroupsSnippets.java#L137)

**Unidades**

* [Obtiene la unidad del usuario con sesión iniciada.](/app/src/main/java/com/microsoft/graph/snippets/snippet/DrivesSnippets.java#L51)
* [Obtiene todos los archivos de la carpeta raíz del usuario.](/app/src/main/java/com/microsoft/graph/snippets/snippet/DrivesSnippets.java#L83)
* [Crea un archivo en la carpeta raíz del usuario.](/app/src/main/java/com/microsoft/graph/snippets/snippet/DrivesSnippets.java#L118)
* [Descarga un archivo desde la carpeta raíz del usuario.](/app/src/main/java/com/microsoft/graph/snippets/snippet/DrivesSnippets.java#L160)
* [Actualiza el contenido de un archivo en la carpeta raíz del usuario.](/app/src/main/java/com/microsoft/graph/snippets/snippet/DrivesSnippets.java#L223)
* [Elimina un archivo en la carpeta raíz del usuario.](/app/src/main/java/com/microsoft/graph/snippets/snippet/DrivesSnippets.java#L284)
* [Cambia el nombre de un archivo en la carpeta raíz del usuario.](/app/src/main/java/com/microsoft/graph/snippets/snippet/DrivesSnippets.java#L337)
* [Crea una carpeta en la carpeta raíz del usuario.](/app/src/main/java/com/microsoft/graph/snippets/snippet/DrivesSnippets.java#L395)

##<a name="device-requirement"></a>Requisitos del dispositivo
Para ejecutar el proyecto de fragmentos de código, el dispositivo debe cumplir los siguientes requisitos:
* Nivel 16 de la API de Android o posterior.

###<a name="prerequisites"></a>Requisitos previos
Para usar el proyecto de fragmentos de código del SDK de Microsoft Graph, necesita lo siguiente:
* La versión más reciente de [Android Studio](http://developer.android.com/sdk/index.html).
* El sistema de automatización de compilación [Gradle](http://www.gradle.org).
* Una cuenta de Office 365. Puede registrarse para [una suscripción a Office 365 Developer](https://portal.office.com/Signup/Signup.aspx?OfferId=C69E7747-2566-4897-8CBA-B998ED3BAB88&DL=DEVELOPERPACK&ali=1#0), que incluye los recursos que necesita para comenzar a crear aplicaciones de Office 365.
* [Kit de desarrollo Java (JDK) 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).
* Una aplicación registrada de Azure con un Id. de cliente y un valor de URI de redirección. Consulte [Conceder permisos a la aplicación de fragmentos de código en Azure](../../../android-java-snippets-rest-sample/wiki/Grant-permissions-for-the-Snippet-application-in-Azure) para obtener más información sobre cómo crear los permisos correctos.

##<a name="azure-client-application-registration"></a>Registro de aplicación de cliente de Azure
1. Inicie sesión en el [Portal de administración de Azure](https://manage.windowsazure.com) usando las credenciales de Azure AD.
2.    Haga clic en **Active Directory** en el menú de la izquierda y, después, seleccione el directorio para el inquilino de Office 365.
3.    En el menú superior, haga clic en **Aplicaciones**.
4.    Haga clic en **Agregar** desde el menú inferior.
5.    En la página **Qué desea hacer**, haga clic en **Agregar una aplicación que mi organización está desarrollando**.
6.    En la página **Háblenos acerca de su aplicación**, especifique **Ejemplo de fragmento de código de Android** para el nombre de la aplicación y seleccione **APLICACIÓN DE CLIENTE NATIVO** para el tipo.
7.    Haga clic en el icono de flecha en la esquina inferior derecha de la página.
8.    En la página **información de la aplicación**, especifique un **URI de redirección**, por ejemplo`http://localhost/androidsnippets` y, después, active la casilla de verificación de la esquina inferior derecha de la página.
9.    Una vez que la aplicación se ha agregado correctamente, se le dirigirá a la página **Inicio rápido** de la aplicación. Desde allí, seleccione **Configurar** en el menú superior.
10. En la sección de permisos a otras aplicaciones, agregue la aplicación Microsoft Graph.
11. Para la aplicación de Microsoft Graph, agregue los siguientes permisos:
   * Iniciar sesión y leer el perfil del usuario
   * Tener acceso completo a todos los archivos a los que puede acceder el usuario
   * Tener acceso completo a los calendarios del usuario
   * Acceso de lectura y escritura al correo del usuario 
   * Enviar el correo como un usuario
   * Leer el correo del usuario
   * Leer y escribir datos del directorio
   * Leer perfiles básicos de todos los usuarios
   * Leer y escribir todos los grupos
13. Haga clic en **Guardar** en el menú inferior.
14. Tenga en cuenta los valores especificados para el **Id. del cliente** en la página **Configurar**. Los necesitará más adelante cuando configure el proyecto.

##<a name="configure-the-project"></a>Configurar el proyecto

1. Descargue o clone el [ejemplo de los fragmentos de código](../../).
2. Inicie Android Studio.
3. Desde el cuadro de diálogo **Bienvenido a Android Studio**, seleccione **Importar proyecto (ADT de Eclipse, Gradle, etc.)**.
4. Seleccione el archivo **settings.gradle** en la carpeta **android-java-snippets-sample** y haga clic en **Aceptar**.
5. Responda al cuadro de diálogo ("sincronización de Gradle: La configuración de Gradle para este proyecto no se ha realizado todavía. ¿Desea que el proyecto utilice el contenedor de Gradle? ") haciendo clic en el botón **Aceptar** para utilizar el contenedor de Gradle. 
6. Abra el archivo ServiceConstants.java en el paquete com.microsoft.graph.snippets.
7. Busque la cadena [`CLIENT_ID`](app/src/main/java/com/microsoft/graph/snippets/ServiceConstants.java#L11) y establezca su valor en el Id. de cliente que registró en Azure.
8. Busque la cadena [`REDIRECT_URI`](app/src/main/java/com/microsoft/graph/snippets/ServiceConstants.java#L10) y establezca su valor en el URI de redirección que registró en Azure.

##<a name="run-the-project"></a>Ejecutar el proyecto
Una vez creado el proyecto, puede ejecutarlo en un emulador o en un dispositivo.

1. Ejecutar el proyecto.
2. Haga clic en el botón **Conectar a Microsoft Graph** para iniciar sesión.
3. Escriba sus credenciales.
4. Revise y acepte los ámbitos de permiso solicitados por la aplicación.
4. Haga clic en una operación en la actividad principal para mostrar los detalles de la operación.
5. Haga clic en el botón **EJECUTAR** para iniciar la operación y esperar a que termine.
6. Haga clic en el cuadro de texto **Objeto sin procesar** para copiar el contenido del cuadro en el portapapeles del dispositivo o emulador.
7. Haga clic en el botón Atrás en la barra de herramientas para volver a la lista de operaciones.
8. (Opcional) Haga clic en el menú de desbordamiento para obtener la opción de menú Desconectar.

##<a name="how-the-sample-affects-your-tenant-data"></a>Repercusión de la muestra en los datos del inquilino
Este ejemplo ejecuta comandos que crean, leen, actualizan o eliminan datos. Cuando ejecuta comandos que eliminan o modifican datos, el ejemplo crea entidades falsas. Las entidades falsas se eliminan o editan, por lo que los datos reales de inquilinos no se ven afectados. El ejemplo dejará atrás algunas de entidades falsas de su inquilino.

##<a name="understand-the-code"></a>Entender el código
El proyecto de fragmentos de código usa estas clases para administrar interacciones con Microsoft Graph:

###<a name="sample-project-organization"></a>Organización del proyecto del ejemplo
El proyecto de fragmentos de código se compone de dos módulos. El diseño modular permite crear una nueva aplicación basada en este ejemplo importando los módulos a su aplicación. Una vez haya importado los módulos, use el código en el módulo de la [aplicación](/app) de fragmentos de código como un ejemplo de cómo llamar métodos en los otros módulos de ejemplo.

###<a name="modules-in-the-snippets-project"></a>Módulos del proyecto de fragmentos de código
* [`o365-Auth`](/o365-auth). Este módulo contiene las llamadas a la biblioteca para autenticar un usuario con Microsoft Graph.
* [`app`](/app). La IU y el módulo de lógica empresarial. Las operaciones de SDK se inician en las clases de fragmentos de código en este módulo.

###<a name="snippet-classes"></a>Clases de fragmento de código
Un fragmento de código ejecuta una única operación y devuelve los resultados. Los fragmentos de código se encuentran en el módulo de la [aplicación](/app). Los fragmentos de código definen el estado necesario para realizar las llamadas en las clases del servicio de Microsoft Graph que se describen a continuación.
* [`AbstractSnippet`](/app/src/main/java/com/microsoft/graph/snippets/snippet/AbstractSnippet.java)
* [`DrivesSnippets`](/app/src/main/java/com/microsoft/graph/snippets/snippet/DrivesSnippets.java)
* [`EventsSnippets`](/app/src/main/java/com/microsoft/graph/snippets/snippet/EventsSnippets.java)
* [`GroupsSnippets`](/app/src/main/java/com/microsoft/graph/snippets/snippet/GroupsSnippets.java)
* [`MeSnippets`](/app/src/main/java/com/microsoft/graph/snippets/snippet/MeSnippets.java)
* [`MessageSnippets`](/app/src/main/java/com/microsoft/graph/snippets/snippet/MessageSnippets.java)
* [`UsersSnippets`](/app/src/main/java/com/microsoft/graph/snippets/snippet/UsersSnippets.java)

###<a name="authentication-classes"></a>Clases de autenticación
Las clases de autenticación se encuentran en el módulo [o365-Auth](/o365-auth). Estas clases usan la [Biblioteca de Azure Active Directory (ADAL) de Microsoft para Android](https://github.com/AzureAD/azure-activedirectory-library-for-android) para conectarse a Microsoft Graph. 

* [`AuthenticationManager`](/o365-auth/src/main/java/com/microsoft/graph/auth/AuthenticationManager.java). Encapsula la lógica de conexión y desconexión del usuario, además de la autorización de la aplicación de Azure.
* [`AzureADModule`](/o365-auth/src/main/java/com/microsoft/graph/auth/AzureADModule.java). Clase auxiliar de autenticación. 
* [`AzureAppCompatActivity`](/o365-auth/src/main/java/com/microsoft/graph/auth/AzureAppCompatActivity.java). Inyección auxiliar de dependencias.
 
<a name="contributing"></a>
## <a name="contributing"></a>Colaboradores ##

Si le gustaría contribuir a este ejemplo, consulte [CONTRIBUTING.MD](/CONTRIBUTING.md).

Este proyecto ha adoptado el [Microsoft Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/) (Código de conducta de código abierto de Microsoft). Para obtener más información, consulte las [Code of Conduct FAQ](https://opensource.microsoft.com/codeofconduct/faq/) (Preguntas más frecuentes del código de conducta) o póngase en contacto con [opencode@microsoft.com](mailto:opencode@microsoft.com) con otras preguntas o comentarios.

## <a name="questions-and-comments"></a>Preguntas y comentarios
Nos encantaría recibir sus comentarios acerca del ejemplo de fragmentos de código de SDK de Microsoft Graph para Android. Puede enviarnos comentarios a través de la sección [Problemas](../../issues) de este repositorio. <br/> Las preguntas generales sobre el desarrollo de Microsoft Graph deben publicarse en [Stack Overflow](http://stackoverflow.com/questions/tagged/microsoftgraph). Asegúrese de que sus preguntas se etiquetan con [microsoftgraph].

## <a name="additional-resources"></a>Recursos adicionales

* [Empiece a trabajar con la API de Office 365 con tecnología de Microsoft Graph](http://dev.office.com/getting-started/office365apis)
* [Información general de Microsoft Graph](http://graph.microsoft.io)
* [SDK de Microsoft Graph para Android](../../../msgraph-sdk-android)
* [Ejemplo de Connect para Android con SDK de Microsoft Graph](../../../android-java-connect-sample)

Copyright (c) 2015 Microsoft. Todos los derechos reservados.
