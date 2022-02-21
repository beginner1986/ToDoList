export default function CenteredSpinner() {
    return (
        <div className="align-items-center d-flex" style={{height: 90+'vh'}}>
            <div className="spinner-border m-5 d-flex justify-content-center">
                <span className="visually-hidden">Loading...</span>
            </div>
        </div>
    );
}