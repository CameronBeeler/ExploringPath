import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.format.DateTimeFormatter;

public
class Main
{
    private static String LOCALFILE = "exploringfile.txt";
    private static String EXAMPLESDIR = "./Examples";
    private static String EXAMPLESDIR1 = "./Examples/Dir1";
    private static String EXAMPLESDIR2 = "./Examples/Dir2";
    private static String EXAMPLESDIR3 = "./Examples/Dir2/Dir3";
    private static String DIRCOUNTS = "./Examples/One/Two/Three/Four";

    private static String FILE1 = "file1.txt";
    private static String COPYFILE1 = "copyfile1.txt";
    private static String FILE2 = "file2.txt";
    private static String COPYFILE2 = "copyfile2.txt";
    private static String FILE3 = "file3.txt";
    private static String COPYFILE3 = "copyfile3.txt";
    private static String FILE4 = "file4.txt";
    private static String COPYFILE4 = "copyfile4.txt";
    private static String RENAMEFILE1 = "newName.txt";


    //    private static String SUBDIRFILE = "/Users/cam/DEV/JAVA/ExploringPath/files/subdirectory.txt";
//private static String SUBDIRFILE = "./files/subdirectory.txt";
    private static String SUBDIRFILE = "subdirectory.txt";
    private static String OUTDIRFILE = "/Users/cam/DEV/javapathtest.txt";
//    private static String OUTDIRFILE = "./../../javapathtest.txt";
//    private static String OUTDIRFILE = "javapathtest.txt";


    public static
    void main(String[] args)
    {
        File workingDirectory = new File("").getAbsoluteFile();
        //  IO
        System.out.println(workingDirectory.getAbsolutePath());
        System.out.println(workingDirectory);

        System.out.println("--------- PRINT DIR1 ----------");
        File dir1file = new File(workingDirectory, "Examples/Dir2");
        String[] dir1contents = dir1file.list();
        for(String dir1:dir1contents)
        {
            System.out.println(dir1);
        }
//        ExploringFileCopyMoveDelete();
//        ExploringFileAttributes();
//        PathWalker();
//        System.out.println("FS Separator ->" + File.separator);
//        System.out.println("FS Separator ->" + FileSystems.getDefault().getSeparator()  );
//        getMountedFileSystems();
    }

    static
    {
    }

    public static
    void PathWalker()
    {
        Path dir = FileSystems.getDefault().getPath("Examples");
        ExploringDirectories(dir);



    }

    public static
    void getMountedFileSystems()
    {
        //MOUNTED FILE SYSTEMS
        Iterable <FileStore> stores = FileSystems.getDefault().getFileStores();
        for(FileStore store:stores)
        {
            System.out.println(store);
            System.out.println(store.name());
        }
        Iterable<Path> rootdir = FileSystems.getDefault().getRootDirectories();
        System.out.println();
        for(Path root:rootdir)
        {
            System.out.println("root Directories -> " + root);
        }

    }

    public static
    void ExploringDirectories(Path dir)
    {
//        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>()
//    {
//        public boolean accept(Path path)
//        throws IOException
//        {
//            return (Files.isDirectory(path));
//
//        }
//    };
//        try(DirectoryStream<Path> contents = Files.newDirectoryStream(dir, "*.txt"))

//        DirectoryStream.Filter<Path> filter = p -> Files.isDirectory(p);
        DirectoryStream.Filter<Path> filter = p -> Files.isRegularFile(p);

        try(DirectoryStream<Path> contents = Files.newDirectoryStream(dir, filter))
    {
            for (Path content:contents)
            {
                BasicFileAttributes attrs       =   Files.readAttributes(content, BasicFileAttributes.class);
                if(attrs.isDirectory())
                {

                    ExploringDirectories(content);
                    System.out.println(content.toString());

                }
                else
                {
                    System.out.println(content.toString());

                }

            }
        }
        catch(IOException | DirectoryIteratorException i)
        {
            i.printStackTrace();
        }
    }


    public static
    void ExploringFileAttributes()
    {
        try
        {
            Path                attrFile    =   FileSystems.getDefault().getPath(OUTDIRFILE);
//            long                filesize    =   Files.size(attrFile);
            BasicFileAttributes attrs       =   Files.readAttributes(attrFile, BasicFileAttributes.class);
            FileTime            createtime  =   attrs.creationTime();
            FileTime            lmtime      =   attrs.lastModifiedTime();
            Object              objKey      =   attrs.fileKey();
            boolean             isdir       =   attrs.isDirectory();
            boolean             isRegFile   =   attrs.isRegularFile();
            long                filesize    =   attrs.size();



            System.out.println("Create Time " + createtime.toString());
            System.out.println("Last Modified Time " + lmtime.toString());
            System.out.println("File Key " + objKey.toString());
            System.out.println(isdir?"This a directory":"This is not a directory");
            System.out.println(isRegFile?"This is a Regular File" : "This is not a regular file");
            System.out.println("File Size " + filesize + " bytes");


        }
        catch(IOException i)
        {
            i.printStackTrace();
        }

    }


    public static
    void ExploringFileCopyMoveDelete()
    {
//        try
//        {
//            Path sourceFile1 = FileSystems.getDefault().getPath(EXAMPLESDIR, RENAMEFILE1);
//            Path sourceFile1 = FileSystems.getDefault().getPath(LOCALFILE);
//            Path sourceFile2 = FileSystems.getDefault().getPath(OUTDIRFILE);
//            Path destFile1 = FileSystems.getDefault().getPath(EXAMPLESDIR, "Dir2", "Dir3", "Dir4", COPYFILE4);
//            Path newPath = FileSystems.getDefault().getPath(DIRCOUNTS);
//            System.out.println(newPath.toString());
//            Files.createDirectory(newPath);
//            Files.createDirectories(newPath);
//            Path destFile2 = FileSystems.getDefault().getPath(EXAMPLESDIR3, RENAMEFILE1);
//            Path moveFile1 = FileSystems.getDefault().getPath(EXAMPLESDIR, RENAMEFILE1);
//            Files.copy(sourceFile1, destFile1);
//            Files.copy(sourceFile2, destFile2);
            //  rename a file
//            Files.copy(sourceFile1, destFile1, StandardCopyOption.REPLACE_EXISTING); // Overwrites existing file...
//            Files.deleteIfExists(destFile1);

            //  move a file
//            Files.move()
//            Files.move(destFile2, moveFile1);


//            Files.deleteIfExists(destFile2);


//        }

//        catch (IOException i)
//        {
//            i.printStackTrace();
//        }
    }

    public static
    void ExploringFileNPath()
    {
        Path path = FileSystems.getDefault().getPath("files", SUBDIRFILE);
//        Path path = FileSystems.getDefault().getPath("/","Users", "cam", "DEV",  OUTDIRFILE);
//        Path path = FileSystems.getDefault().getPath("files", SUBDIRFILE);
//        System.out.println(new String(path.getRoot()));
        System.out.println("\n" + path.toAbsolutePath());
        FileReader(path);

        Path filePath = Paths.get("..", "..", "javapathtest.txt");
        System.out.println("\n" + path.toAbsolutePath());
        FileReader(filePath);

        Path fakePath = FileSystems.getDefault().getPath("thisisacompletelybogusfile.txt");
        System.out.println("Fakepath - does this file exist? " + Files.exists(fakePath));
        System.out.println("Filepath - does this file exist? " + Files.exists(filePath));


    }

    public static
    void FileReader(Path path)
    {
        try(BufferedReader fileReader = Files.newBufferedReader(path))
        {
            String lineOfFile = null;
            while((lineOfFile = fileReader.readLine())!=null)
            {
                System.out.println(lineOfFile);
            }
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
}
